package com.epam.webelecty.services;

import com.epam.webelecty.models.Course;
import com.epam.webelecty.models.StudentCourse;
import com.epam.webelecty.models.User;

public interface ListStudentsService {

    StudentCourse postMarkAndAnnotation(int mark, User user, Course course);

}
