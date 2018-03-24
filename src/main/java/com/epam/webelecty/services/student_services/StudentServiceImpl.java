package com.epam.webelecty.services.student_services;

import com.epam.webelecty.models.Course;
import com.epam.webelecty.models.CourseStatus;
import com.epam.webelecty.models.StudentCourse;
import com.epam.webelecty.models.User;
import com.epam.webelecty.persistence.dao.CourseDAO;
import com.epam.webelecty.persistence.dao.StudentCourseDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Map;
import java.util.Set;

@Service
public class StudentServiceImpl implements StudentService {

    private final StudentCourseDAO studentCourseDAO;

    private final CourseDAO courseDAO;

    @Autowired
    public StudentServiceImpl(StudentCourseDAO studentCourseDAO, CourseDAO courseDAO) {
        this.studentCourseDAO = studentCourseDAO;
        this.courseDAO = courseDAO;
    }

    public Set<Course> getAvailableCourses(User user) {
        return studentCourseDAO.getAllAvailableCoursesByStudent(user);
    }

    @Override
    public Set<Course> getWaitedCourses(User user) {
        return studentCourseDAO.getWaitedCourses(user);
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
            default:
                courseSet = studentCourseDAO.getAllCoursesByStudent(user);
        }
        return courseSet;
    }

    private Set<Course> getAllPlannedCourses() {
        Set<Course> courseSet = new HashSet<>();
        for (Course course : courseDAO.getAllEntries()) {
            if (course.getStatus() == CourseStatus.PLANNED) courseSet.add(course);
        }
        return courseSet;
    }

    private Set<Course> getAllFinishedCourses(User user) {
        Set<Course> courseSet = new HashSet<>();
        for (Course course : studentCourseDAO.getAllCoursesByStudent(user)) {
            if (course.getStatus() == CourseStatus.FINISHED) courseSet.add(course);
        }
        return courseSet;
    }

    private Set<Course> getMyActiveCourses(User user) {
        Set<Course> courseSet = new HashSet<>();
        for (Course course : studentCourseDAO.getAllCoursesByStudent(user)) {
            if (course.getStatus() == CourseStatus.ACTIVE) courseSet.add(course);
        }
        return courseSet;
    }

    @Override
    public StudentCourse joinCourse(User user, int courseId) {
        return studentCourseDAO.insert(user, courseDAO.getById(courseId));
    }

    public Map<Course, StudentCourse> getFinishedCoursesMap(User user) {
        return studentCourseDAO.getFinishedCoursesMap(user);
    }
}
