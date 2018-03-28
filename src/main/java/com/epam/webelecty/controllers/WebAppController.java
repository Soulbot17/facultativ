package com.epam.webelecty.controllers;

import com.epam.webelecty.models.User;
import com.epam.webelecty.models.UserDTO;
import com.epam.webelecty.models.UserRole;
import com.epam.webelecty.services.UserService;
import com.epam.webelecty.services.exeptions.EmailIsUsedException;
import com.epam.webelecty.services.exeptions.RegisterDataException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.Locale;
import java.util.ResourceBundle;

@Controller
public class WebAppController {

    @Autowired
    UserService userService;

    @Autowired
    private MessageSource messageSource;

    @GetMapping(value = "/")
    public String redirectUserPage() {
        return "redirect:/user";
    }

    @RequestMapping(value = "index", method = {RequestMethod.GET})
    public ModelAndView getIndexPage() {
        return new ModelAndView("index");
    }

    @RequestMapping(value = EndPointsAPI.INDEX_PAGE, method = {RequestMethod.POST})
    public String sendUserPage() {
        return "redirect:/user";
    }


    @RequestMapping(value = EndPointsAPI.USER_PAGE, method = RequestMethod.GET)
    public String getUserPage() {
        User user = userService.getCurrentUser();
        if (UserRole.TUTOR == user.getRole()) {
            return "redirect:/user_tutor";
        }
        return "redirect:/user_student";
    }

    @RequestMapping(value = EndPointsAPI.USER_PAGE, method = RequestMethod.POST)
    public ModelAndView getUserPagePost(@ModelAttribute("userForm") User userForm) {
        return new ModelAndView("user").addObject("name", userForm.getName());
    }

    @RequestMapping(value = EndPointsAPI.REGISTRATION_PAGE, method = RequestMethod.GET)
    public String registration(Model model) {
        model.addAttribute("userForm", new UserDTO());
        return "registration";
    }

    @RequestMapping(value = EndPointsAPI.REGISTRATION_PAGE, method = RequestMethod.POST)
    public String registration(@ModelAttribute("userForm") UserDTO userForm) {
        userService.register(userForm);
        return "redirect:/user";
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

    @GetMapping(value = EndPointsAPI.LOGIN_PAGE)
    public ModelAndView getLoginPage(Authentication authentication, String error, String logout, Locale locale) {
        if (authentication != null) {
            return new ModelAndView("redirect:/index");
        }
        ModelAndView modelAndView = new ModelAndView("login");
        if (error != null) {
            modelAndView.addObject("error", messageSource.getMessage("label.error", new Object[]{}, locale));
        }

        if (logout != null) {
            modelAndView.addObject("message", messageSource.getMessage("label.log_out", new Object[]{}, locale));
        }

        return modelAndView;
    }
}