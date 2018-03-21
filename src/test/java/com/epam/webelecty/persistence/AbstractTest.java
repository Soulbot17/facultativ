package com.epam.webelecty.persistence;

import com.epam.webelecty.config.AppConfig;
import com.epam.webelecty.config.DBConfig;
import com.epam.webelecty.models.Course;
import com.epam.webelecty.models.StudentCourse;
import com.epam.webelecty.persistence.dao.CourseDAO;
import com.epam.webelecty.persistence.dao.StudentCourseDAO;
import com.epam.webelecty.persistence.dao.UserDAO;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {AppConfig.class, DBConfig.class, StudentCourseDAO.class, UserDAO.class, CourseDAO.class})
@WebAppConfiguration
public abstract class AbstractTest {
}
