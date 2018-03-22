package com.epam.webelecty.services;

import com.epam.webelecty.models.Course;
import com.epam.webelecty.models.User;
import com.epam.webelecty.persistence.dao.CourseDAO;
import com.epam.webelecty.persistence.dao.StudentCourseDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Set;

@Service
public class TutorServiceImpl implements TutorService {

    @Autowired
    CourseDAO courseDAO;

    @Autowired
    StudentCourseDAO studentCourseDAO;

    @Override
    public Set<Course> getCourses(User user) {
        Set<Course> courses = studentCourseDAO.getAllCoursesByTutor(user);
        return courses;
    }

    @Override
    public Set<User> getStudents(Course course) {
        Set<User> students = studentCourseDAO.getAllStudentsByCourse(course);
        return students;
    }
}
