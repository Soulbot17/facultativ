package com.epam.webelecty.services;

import com.epam.webelecty.models.Course;
import com.epam.webelecty.models.User;
import org.springframework.web.servlet.ModelAndView;

import java.util.Set;

public interface TutorService {

    Set<Course> getCourses(User user);

    Course getCourseById(Integer courseId);

    Set<User> getStudents(Course course);

    Course updateCourse(Course course);

    ModelAndView fillModelAndView();

}
