package com.epam.webelecty.controllers;

import com.epam.webelecty.services.TutorService;
import com.epam.webelecty.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class TutorController {

    @Autowired
    UserService userService;

    @Autowired
    TutorService tutorService;

    @GetMapping(value = "/user_tutor")
    public ModelAndView getUserPage() {
        return tutorService.fillModelAndView();
    }

    @PostMapping(value = "/user_tutor")
    public ModelAndView setCourseStatusClose(@ModelAttribute("course") Integer courseId){
        tutorService.updateCourse(courseId);
        return getUserPage();
    }
}
