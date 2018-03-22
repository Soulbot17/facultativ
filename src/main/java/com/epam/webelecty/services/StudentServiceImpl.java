package com.epam.webelecty.services;

import com.epam.webelecty.models.Course;
import com.epam.webelecty.models.CourseStatus;
import com.epam.webelecty.models.StudentCourse;
import com.epam.webelecty.models.User;
import com.epam.webelecty.persistence.dao.CourseDAO;
import com.epam.webelecty.persistence.dao.StudentCourseDAO;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;

@Service
public class StudentServiceImpl implements StudentService {

    private final StudentCourseDAO studentCourseDAO;

    private final CourseDAO courseDAO;

    public StudentServiceImpl(StudentCourseDAO studentCourseDAO, CourseDAO courseDAO) {
        this.studentCourseDAO = studentCourseDAO;
        this.courseDAO = courseDAO;
    }

    @Override
    public Set<Course> getAvailableCourses(User user) {
        Set<Course> availableCourses = new HashSet<>();
        Set<Course> userCourses = studentCourseDAO.getAllCoursesByStudent(user);
        Set<Course> allCourses = courseDAO.getAllEntries();
        for (Course course : allCourses) {
            boolean isPlanned = course.getStatus() == CourseStatus.PLANNED;
            if (!userCourses.contains(course) && isPlanned) availableCourses.add(course);
        }
        return availableCourses;
    }

    @Override
    public Set<Course> getWaitedCourses(User user) {
        Set<Course> waitedCourses = new HashSet<>();
        Set<StudentCourse> courseSet = studentCourseDAO.getAllEntries();
        for (StudentCourse course : courseSet) {
            boolean isPlanned = courseDAO.getById(course.getCourseId()).getStatus() == CourseStatus.PLANNED;
            boolean isWaited = user.getUserId() == course.getStudentId();
            if (isPlanned && isWaited) waitedCourses.add(courseDAO.getById(course.getCourseId()));
        }
        return waitedCourses;
    }

    @Override
    public Set<Course> getCourses(User user, CourseStatus status) {
        Set<Course> courseSet;
        switch (status) {
            case FINISHED:
                courseSet = getAllFinishedCourses(user);
                break;
            case PLANNED:
                courseSet = getAllPlannedCourses();
                break;
            case ACTIVE:
                courseSet = getMyActiveCourses(user);
                break;
            default: courseSet = studentCourseDAO.getAllCoursesByStudent(user);
        }
        return courseSet;
    }

    private Set<Course> getAllPlannedCourses() {
        Set<Course> courseSet = new HashSet<>();
        for (Course course: courseDAO.getAllEntries()) {
            if (course.getStatus() == CourseStatus.PLANNED) courseSet.add(course);
        }
        return courseSet;
    }

    private Set<Course> getAllFinishedCourses(User user) {
        Set<Course> courseSet = new HashSet<>();
        for (Course course: studentCourseDAO.getAllCoursesByStudent(user)) {
            if (course.getStatus() == CourseStatus.FINISHED) courseSet.add(course);
        }
        return courseSet;
    }

    private Set<Course> getMyActiveCourses(User user) {
        Set<Course> courseSet = new HashSet<>();
        for (Course course: studentCourseDAO.getAllCoursesByStudent(user)) {
            if (course.getStatus() == CourseStatus.ACTIVE) courseSet.add(course);
        }
        return courseSet;
    }


    @Override
    public StudentCourse joinCourse(User user, Course course) {
        return studentCourseDAO.insert(user, course);
    }

    @Override
    public StudentCourse getMarkAndAnnotationByCourseName(User user, Course course) {
        Set<StudentCourse> allEntries = studentCourseDAO.getAllEntries();
        for (StudentCourse sc : allEntries) {
            boolean checkStudentId = sc.getStudentId() == user.getUserId();
            boolean checkCourseId = sc.getCourseId() == course.getCourseId();
            boolean isFinished = course.getStatus() == CourseStatus.FINISHED;
            if (checkStudentId && checkCourseId && isFinished) return sc;
        }
        return null;
    }
}
