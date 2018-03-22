package com.epam.webelecty.controllers;

import com.epam.webelecty.models.Course;
import com.epam.webelecty.models.User;
import com.epam.webelecty.services.TutorService;
import com.epam.webelecty.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.Set;

@Controller
public class TutorController {

    @Autowired
    UserService userService;

    @Autowired
    TutorService tutorService;

    @GetMapping(value = "/user_tutor")
    public ModelAndView getUserPage() {
        User tutor = userService.getRoleByEmail();
        Set<Course> courses = tutorService.getCourses(tutor);
//        Set<User> students = tutorService.getStudents(courses);

        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("user_tutor");

        modelAndView.addObject("UserName", tutor.getName());
        modelAndView.addObject("UserLastName", tutor.getLastName());
        modelAndView.addObject("Courses", courses);
//        modelAndView.addObject("Students", s)
        return modelAndView;
    }
}
