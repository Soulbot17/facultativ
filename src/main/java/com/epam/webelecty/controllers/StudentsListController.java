package com.epam.webelecty.controllers;


import com.epam.webelecty.models.Course;
import com.epam.webelecty.models.User;
import com.epam.webelecty.services.StudentsListService;
import com.epam.webelecty.services.TutorService;
import com.epam.webelecty.services.UserService;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
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

    @Autowired
    StudentsListService studentsListService;

    @GetMapping(value = "/student_list")
    public ModelAndView getStudentListByCourseId(@ModelAttribute("course") Integer courseId) {
        Course course = tutorService.getCourseById(courseId);
        Set<User> students = tutorService.getStudents(course);
        User tutor = userService.getCurrentUser();
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("student_list");

        modelAndView.addObject("CourseName", course.getCourseName());
        modelAndView.addObject("CourseId", course.getCourseId());
        modelAndView.addObject("UserLastName", tutor.getLastName());
        modelAndView.addObject("Students", students);
        modelAndView.addObject("course", new UserFeedback());
        return modelAndView;
    }

    @PostMapping(value = "/student_list")
    public ModelAndView addFeedback(@ModelAttribute("course") UserFeedback userFeedback) {
        studentsListService.postMarkAndAnnotation(
                userFeedback.studentId,
                userFeedback.courseId,
                userFeedback.mark,
                userFeedback.feedback);
        return getStudentListByCourseId(userFeedback.courseId);
    }

    @Data
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    static class UserFeedback {
        private Integer studentId;
        private Integer courseId;
        private Integer mark;
        private String feedback;
    }
}
