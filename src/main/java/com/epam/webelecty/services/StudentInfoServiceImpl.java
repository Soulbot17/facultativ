package com.epam.webelecty.services;

import com.epam.webelecty.models.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.ModelAndView;

@Service
public class StudentInfoServiceImpl implements StudentInfoService{

    @Autowired
    UserService userService;

    @Override
    public ModelAndView fillModelAndView(){
        User student = userService.getRoleByEmail();

        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("student_info");
        modelAndView.addObject("StudentName", student.getName());
        modelAndView.addObject("StudentLastName", student.getLastName());
        return modelAndView;
    }
}
