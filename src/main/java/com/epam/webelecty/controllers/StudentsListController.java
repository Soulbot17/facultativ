package com.epam.webelecty.controllers;


import com.epam.webelecty.models.Course;
import com.epam.webelecty.models.User;
import com.epam.webelecty.services.TutorService;
import com.epam.webelecty.services.UserService;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.Set;

@Log4j2
@Controller
public class StudentsListController {
    @Autowired
    UserService userService;

    @Autowired
    TutorService tutorService;

    @GetMapping(value = "/student_list")
    public ModelAndView getStudentListByCourseId(@ModelAttribute("course") Integer courseId, Model model) {
        Course course = tutorService.getCourseById(courseId);
        Set<User> students = tutorService.getStudents(course);
        User tutor = userService.getRoleByEmail();
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("student_list");

        modelAndView.addObject("CourseName", course.getCourseName());
        modelAndView.addObject("UserLastName", tutor.getLastName());
        modelAndView.addObject("Students", students);
        model.addAttribute("course", new UserFeedback());
        return modelAndView;
    }

    @PostMapping(value = "/student_list")
    public Model addFeedback(@ModelAttribute("course") UserFeedback userFeedback, Model model) {
        log.debug(userFeedback.feedback);
        log.debug(userFeedback.mark);
        return model;
    }

    @Data
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    static class UserFeedback {
        private String mark;
        private String feedback;

//        public UserFeedback() {
//        }

        //        public UserFeedback(String mark, String feedback) {
//            this.mark = mark;
//            this.feedback = feedback;
//        }
    }

}
