package com.epam.webelecty.controllers;

import com.epam.webelecty.models.User;
import com.epam.webelecty.models.UserRole;
import com.epam.webelecty.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import static com.epam.webelecty.controllers.EndPointsAPI.*;

@Controller
public class TestController {

    @Autowired
    UserService userService;

    @RequestMapping(value = {"/", INDEX_PAGE}, method = {RequestMethod.GET})
    public ModelAndView getWelcomePage() {

        return new ModelAndView(INDEX_PAGE);
    }

    @RequestMapping(value = USER_PAGE, method = RequestMethod.GET)
    @ResponseBody
    public ModelAndView getUserPage() {
        ModelAndView modelAndView = new ModelAndView();
        User user = userService.getRoleByEmail();
        if(UserRole.TUTOR==user.getRole()){
            modelAndView.setViewName(TUTOR_PAGE);
            modelAndView.addObject("UserName", userService.getRoleByEmail().getName());
            return modelAndView;
        }
        modelAndView = new ModelAndView(STUDENT_PAGE);
        modelAndView.addObject("UserName", userService.getRoleByEmail().getName());
        return modelAndView;
    }

    @RequestMapping(value = USER_PAGE, method = RequestMethod.POST)
    public ModelAndView getUserPagePost(@ModelAttribute("userForm") User userForm) {
        return new ModelAndView(USER_PAGE).addObject("name", userForm.getName());
    }

    @RequestMapping(value = REGISTRATION_PAGE, method = RequestMethod.GET)
    public String registration(Model model) {
        model.addAttribute("userForm", new User());

        return REGISTRATION_PAGE;
    }

    @RequestMapping(value = REGISTRATION_PAGE, method = RequestMethod.POST)
    @ResponseBody
    public String registration(@ModelAttribute("userForm") User userForm, BindingResult bindingResult, Model model) {
        return "Welcome " + userForm.toString();
    }
}
