package com.epam.webelecty.services;

import com.epam.webelecty.models.Course;
import com.epam.webelecty.models.StudentCourse;
import com.epam.webelecty.models.User;
import com.epam.webelecty.persistence.dao.CourseDAO;
import com.epam.webelecty.persistence.dao.StudentCourseDAO;
import com.epam.webelecty.persistence.dao.UserDAO;
import org.springframework.beans.factory.annotation.Autowired;

public class ListStudentsServiceImpl implements ListStudentsService {
    @Autowired
    CourseDAO courseDAO;

    @Autowired
    StudentCourseDAO studentCourseDAO;

    @Autowired
    UserDAO userDAO;

    @Override
    public StudentCourse postMarkAndAnnotation(int mark, User user, Course course) {
        return null;
    }
}
