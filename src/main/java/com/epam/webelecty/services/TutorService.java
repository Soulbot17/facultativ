package com.epam.webelecty.services;

import com.epam.webelecty.models.Course;
import com.epam.webelecty.models.User;

import java.util.Set;

public interface TutorService {

    Set<Course> getCourses(User user);

    Set<User> getStudents(Course course);

}
