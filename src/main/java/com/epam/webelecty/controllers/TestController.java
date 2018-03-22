package com.epam.webelecty.controllers;

import com.epam.webelecty.models.User;
import com.epam.webelecty.models.UserDTO;
import com.epam.webelecty.models.UserRole;
import com.epam.webelecty.services.UserService;
import com.epam.webelecty.services.exeptions.EmailIsUsedException;
import com.epam.webelecty.services.exeptions.RegisterDataException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class TestController {

    @Autowired
    UserService userService;

    @GetMapping(value = "/")
    public String redirectUserPage() {
        return "redirect:/index";
    }

    @RequestMapping(value = "index", method = {RequestMethod.GET})
    public ModelAndView getIndexPage() {
        return new ModelAndView("index");
    }
    @RequestMapping(value = {"/index"}, method = {RequestMethod.POST})
    public String sendUserPage() {
        return "redirect:/user";
    }



    @RequestMapping(value = "/user", method = RequestMethod.GET)
    @ResponseBody
    public ModelAndView getUserPage() {
        ModelAndView modelAndView = new ModelAndView();
        User user = userService.getRoleByEmail();
        if(UserRole.TUTOR==user.getRole()){
            modelAndView.setViewName("user_tutor");
            modelAndView.addObject("UserName", userService.getRoleByEmail().getName());
            return modelAndView;
        }
        modelAndView = new ModelAndView("user_student");
        modelAndView.addObject("UserName", userService.getRoleByEmail().getName());
        return modelAndView;
    }

    @RequestMapping(value = "/user", method = RequestMethod.POST)
    public ModelAndView getUserPagePost(@ModelAttribute("userForm") User userForm) {
        return new ModelAndView("user").addObject("name", userForm.getName());
    }

    @RequestMapping(value = "/registration", method = RequestMethod.GET)
    public String registration(Model model) {
        model.addAttribute("userForm", new UserDTO());
        return "registration";
    }

    @RequestMapping(value = "/registration", method = RequestMethod.POST)
    public String registration(@ModelAttribute("userForm") UserDTO userForm) {
        userService.register(userForm);
        return "redirect:/index";
    }

    @ExceptionHandler(EmailIsUsedException.class)
    public String emailError(Model model) {
        model.addAttribute("error", "Email is registered");
        model.addAttribute("userForm", new UserDTO());
        return "registration";
    }


    @ExceptionHandler(RegisterDataException.class)
    public String regDataError(Model model) {
        model.addAttribute("error", "Error in register data");
        model.addAttribute("userForm", new UserDTO());
        return "registration";
    }

    @GetMapping(value = "/login")
    public ModelAndView getLoginPage() {
        if(!"anonymousUser".equals(SecurityContextHolder.getContext().getAuthentication().getPrincipal())){
            return new ModelAndView("redirect:/index");
        }
        return new ModelAndView("login");
    }
}