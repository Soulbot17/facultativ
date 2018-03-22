package com.epam.webelecty.controllers;

import com.epam.webelecty.models.Course;
import com.epam.webelecty.models.CourseStatus;
import com.epam.webelecty.models.StudentCourse;
import com.epam.webelecty.models.User;
import com.epam.webelecty.persistence.dao.CourseDAO;
import com.epam.webelecty.services.StudentServiceImpl;
import com.epam.webelecty.services.UserService;
import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

@Controller
public class StudentController {

    @Autowired
    UserService userService;

    @Autowired
    StudentServiceImpl studentService;

    @Autowired
    CourseDAO courseDAO;

    @GetMapping(value = "/user_student")
    @SneakyThrows
    public ModelAndView getUserPage() {
        ModelAndView modelAndView = new ModelAndView("user_student");
        User currentUser = userService.getRoleByEmail();

        Set<Course> plannedCourses = studentService.getCourses(currentUser, CourseStatus.PLANNED);
        Set<Course> activeCourses = studentService.getCourses(currentUser, CourseStatus.ACTIVE);
        Set<Course> finishedCourses = studentService.getCourses(currentUser, CourseStatus.FINISHED);
        Map<Course, StudentCourse> finishedMap = new HashMap<>();

        for (Course course : finishedCourses)
            finishedMap.put(course, studentService.getMarkAndAnnotationByCourseName(currentUser, course));

        modelAndView.addObject("userName", currentUser.getName());
        modelAndView.addObject("userLastName", currentUser.getLastName());
        modelAndView.addObject("plannedCourses", plannedCourses);
        modelAndView.addObject("activeCourses", activeCourses);
        modelAndView.addObject("finishedMap", finishedMap);


        return modelAndView;
    }

    @RequestMapping(value = "/user_student", method = RequestMethod.POST)
    public ModelAndView addUserCourse(@ModelAttribute("course") Integer id) {
            studentService.joinCourse(userService.getRoleByEmail(), courseDAO.getById(id));
        ModelAndView modelAndView = new ModelAndView("user_student");
        User currentUser = userService.getRoleByEmail();

        Set<Course> plannedCourses = studentService.getAvailableCourses(currentUser);
        Set<Course> waitedCourses = studentService.getWaitedCourses(currentUser);
        Set<Course> activeCourses = studentService.getCourses(currentUser, CourseStatus.ACTIVE);
        Set<Course> finishedCourses = studentService.getCourses(currentUser, CourseStatus.FINISHED);
        Map<Course, StudentCourse> finishedMap = new HashMap<>();

        for (Course course : finishedCourses)
            finishedMap.put(course, studentService.getMarkAndAnnotationByCourseName(currentUser, course));

        modelAndView.addObject("userName", currentUser.getName());
        modelAndView.addObject("userLastName", currentUser.getLastName());
        modelAndView.addObject("plannedCourses", plannedCourses);
        modelAndView.addObject("activeCourses", activeCourses);
        modelAndView.addObject("waitedCourses", waitedCourses);
        modelAndView.addObject("finishedMap", finishedMap);
        return modelAndView;
    }
}
