package com.epam.webelecty.controllers;

import com.epam.webelecty.models.Course;
import com.epam.webelecty.models.CourseStatus;
import com.epam.webelecty.models.StudentCourse;
import com.epam.webelecty.models.User;
import com.epam.webelecty.services.student_services.StudentServiceImpl;
import com.epam.webelecty.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import java.util.Map;
import java.util.Set;

@Controller
public class StudentController {


    private UserService userService;

    private StudentServiceImpl studentService;

    @Autowired
    public StudentController(UserService userService, StudentServiceImpl studentService) {
        this.userService = userService;
        this.studentService = studentService;
    }

    @GetMapping(value = EndPointsAPI.STUDENT_PAGE)
    public ModelAndView getUserPage() {
        return getDefaultModelAndView();
    }


    @RequestMapping(value = EndPointsAPI.STUDENT_PAGE, method = RequestMethod.POST)
    public ModelAndView addUserCourse(@ModelAttribute("course") Integer id) {
        studentService.joinCourse(userService.getCurrentUser(), id);
        return getDefaultModelAndView();
    }

    private ModelAndView getDefaultModelAndView() {
        ModelAndView modelAndView = new ModelAndView("user_student");
        User currentUser = userService.getCurrentUser();

        Set<Course> availableCourses = studentService.getAvailableCourses(currentUser);
        Set<Course> waitedCourses = studentService.getWaitedCourses(currentUser);
        Set<Course> activeCourses = studentService.getCourses(currentUser, CourseStatus.ACTIVE);
        Map<Course, StudentCourse> finishedCoursesMap = studentService.getFinishedCoursesMap(currentUser);

        modelAndView.addObject("userName", currentUser.getName());
        modelAndView.addObject("userLastName", currentUser.getLastName());
        modelAndView.addObject("availableCourses", availableCourses);
        modelAndView.addObject("waitedCourses", waitedCourses);
        modelAndView.addObject("activeCourses", activeCourses);
        modelAndView.addObject("finishedMap", finishedCoursesMap);
        return modelAndView;
    }


}
