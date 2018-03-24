package com.epam.webelecty.services;

import com.epam.webelecty.models.Course;
import com.epam.webelecty.models.StudentCourse;
import com.epam.webelecty.models.User;
import com.epam.webelecty.persistence.dao.CourseDAO;
import com.epam.webelecty.persistence.dao.StudentCourseDAO;
import com.epam.webelecty.persistence.dao.UserDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Map;

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

    @Override
    public Map<User, StudentCourse> getMapUserStudentCourseByCourse(Course course, boolean hasFeedback) {
        return studentCourseDAO.getMapStudentCoursesByCourse(course, hasFeedback);
    }
}

