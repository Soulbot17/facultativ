package com.epam.webelecty.controllers;

import com.epam.webelecty.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class StudentController {

    @Autowired
    UserService userService;

    @GetMapping(value = "/user_student")
    public ModelAndView getUserPage() {
        ModelAndView modelAndView = new ModelAndView("user_student");;
        modelAndView.addObject("UserName", userService.getRoleByEmail().getName());
        return modelAndView;
    }
}
