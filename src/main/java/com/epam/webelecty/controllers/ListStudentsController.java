package com.epam.webelecty.controllers;


import com.epam.webelecty.models.Course;
import com.epam.webelecty.models.User;
import com.epam.webelecty.services.TutorService;
import com.epam.webelecty.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.Set;

@Controller
public class ListStudentsController {
    @Autowired
    UserService userService;

    @Autowired
    TutorService tutorService;

    @GetMapping(value = "/student_list")
    public ModelAndView getStudentListByCourseId(@ModelAttribute("course") Integer courseId) {
        Course course = tutorService.getCourseById(courseId);
        Set<User> students = tutorService.getStudents(course);
        User tutor = userService.getRoleByEmail();
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("student_list");

        modelAndView.addObject("CourseName", course.getCourseName());
        modelAndView.addObject("UserLastName", tutor.getLastName());
        modelAndView.addObject("Students", students);
        return modelAndView;
    }

    //TODO: реализовать метод для оставления фидбека
    @PostMapping(value = "/student_list")
    public ModelAndView postMarkAndFeedback(){
        return new ModelAndView();
    }


}
