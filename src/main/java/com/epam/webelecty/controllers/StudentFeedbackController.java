package com.epam.webelecty.controllers;

import com.epam.webelecty.services.StudentInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;



@Controller
public class StudentFeedbackController {

    @Autowired
    StudentInfoService studentInfoService;

    @GetMapping(value = "/student_feedback")
    public ModelAndView getUserPage() {
        return studentInfoService.fillModelAndView();
    }
}
