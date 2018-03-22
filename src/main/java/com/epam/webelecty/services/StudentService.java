package com.epam.webelecty.services;


import com.epam.webelecty.models.Course;
import com.epam.webelecty.models.CourseStatus;
import com.epam.webelecty.models.StudentCourse;
import com.epam.webelecty.models.User;

import java.util.Set;

public interface StudentService {

    Set<Course> getAvailableCourses(User user);
    Set<Course> getWaitedCourses(User user);
    Set<Course> getCourses(User user, CourseStatus status);
    StudentCourse joinCourse(User user, int courseId);
    StudentCourse getMarkAndAnnotationByCourseName(int userId, Course course);

}
