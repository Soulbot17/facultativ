package com.epam.webelecty.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class TestController {

    @RequestMapping(value = {"/", "/index"}, method = { RequestMethod.GET })
    public ModelAndView getWelcomePage(){
        return new ModelAndView("index");
    }

    @RequestMapping(value = "/user", method = RequestMethod.GET)
    public ModelAndView getUserPage(){
        return new ModelAndView("user");
    }
}
