package com.epam.webelecty.services.tutor_services;

import com.epam.webelecty.models.Course;
import com.epam.webelecty.models.CourseStatus;
import com.epam.webelecty.models.User;
import com.epam.webelecty.persistence.dao.CourseDAO;
import com.epam.webelecty.persistence.dao.StudentCourseDAO;
import com.epam.webelecty.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.ModelAndView;

import java.util.Set;

@Service
public class TutorServiceImpl implements TutorService {

    @Autowired
    CourseDAO courseDAO;

    @Autowired
    StudentCourseDAO studentCourseDAO;

    @Autowired
    UserService userService;

    @Override
    public Set<Course> getUnfinishedCourses(User user) {
        return studentCourseDAO.getAllUnfinishedCoursesByTutor(user);
    }

    @Override
    public Set<Course> getFinishedCourses(User user) {
        return studentCourseDAO.getAllFinishedCoursesByTutor(user);
    }

    @Override
    public Course getCourseById(Integer courseId) {
        return courseDAO.getById(courseId);
    }

    @Override
    public Set<User> getStudents(Course course) {
        return studentCourseDAO.getAllStudentsByCourse(course);
    }

    @Override
    public Course updateCourse(int courseId) {
        Course course = getCourseById(courseId);
        course.setStatus(CourseStatus.FINISHED);
        return courseDAO.updateEntry(course);
    }

    @Override
    public ModelAndView fillModelAndView() {
        User tutor = userService.getCurrentUser();
        Set<Course> unfinishedCourses = getUnfinishedCourses(tutor);
        Set<Course> finishedCourses = getFinishedCourses(tutor);

        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("user_tutor");
        modelAndView.addObject("UserName", tutor.getName());
        modelAndView.addObject("UserLastName", tutor.getLastName());
        modelAndView.addObject("unfinishedCourses", unfinishedCourses);
        modelAndView.addObject("finishedCourses", finishedCourses);

        return modelAndView;
    }

}
