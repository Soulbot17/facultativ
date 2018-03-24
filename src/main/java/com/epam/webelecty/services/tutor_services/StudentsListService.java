package com.epam.webelecty.services.tutor_services;

import com.epam.webelecty.models.Course;
import com.epam.webelecty.models.StudentCourse;
import com.epam.webelecty.models.User;

import java.util.Map;

public interface StudentsListService {

    StudentCourse postMarkAndAnnotation(int userId, int courseId, int mark, String feedback);

    Map<User, StudentCourse> getMapUserStudentCourseByCourse(Course course, boolean hasFeedback);

}
