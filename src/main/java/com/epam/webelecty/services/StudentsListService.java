package com.epam.webelecty.services;

import com.epam.webelecty.models.StudentCourse;

public interface StudentsListService {

    StudentCourse postMarkAndAnnotation(int userId, int courseId, int mark, String feedback);

}
