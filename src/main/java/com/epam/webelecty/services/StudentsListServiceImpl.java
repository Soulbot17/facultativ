package com.epam.webelecty.services;

import com.epam.webelecty.models.StudentCourse;
import com.epam.webelecty.persistence.dao.CourseDAO;
import com.epam.webelecty.persistence.dao.StudentCourseDAO;
import com.epam.webelecty.persistence.dao.UserDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class StudentsListServiceImpl implements StudentsListService {
    @Autowired
    CourseDAO courseDAO;

    @Autowired
    StudentCourseDAO studentCourseDAO;

    @Autowired
    UserDAO userDAO;

    @Override
    public StudentCourse postMarkAndAnnotation(int userId, int courseId, int mark, String feedback) {
        return studentCourseDAO.postMarkAndAnnotation(userId, courseId, mark, feedback);
    }
}
