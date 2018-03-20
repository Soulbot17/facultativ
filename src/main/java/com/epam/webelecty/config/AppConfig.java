package com.epam.webelecty.config;

import com.epam.webelecty.controllers.TestController;
import com.epam.webelecty.persistence.dao.UserDAO;
import com.epam.webelecty.persistence.dao.UserDAOImpl;
import com.epam.webelecty.persistence.database.ConnectionPool;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.view.InternalResourceViewResolver;
import org.springframework.web.servlet.view.JstlView;

@EnableWebMvc
@Configuration
@ComponentScan({ "com.epam.webelecty.*" })
@Import({DBConfig.class})
public class AppConfig {

    @Autowired
    ConnectionPool connectionPool;

    @Bean
    public InternalResourceViewResolver resolver() {
        InternalResourceViewResolver internalResourceViewResolver = new InternalResourceViewResolver();
        internalResourceViewResolver.setViewClass(JstlView.class);
        internalResourceViewResolver.setPrefix("/WEB-INF/pages/");
        internalResourceViewResolver.setSuffix(".jsp");
        return internalResourceViewResolver;
    }

    @Bean
    public TestController testController() {
        return new TestController();
    }

    @Bean
    public UserDAO userDao() {
        return new UserDAOImpl(connectionPool);
    }

}
