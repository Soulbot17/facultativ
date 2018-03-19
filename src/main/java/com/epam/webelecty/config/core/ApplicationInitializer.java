package com.epam.webelecty.config.core;

import com.epam.webelecty.config.AppConfig;
import org.springframework.web.WebApplicationInitializer;
import org.springframework.web.context.ContextLoaderListener;
import org.springframework.web.context.support.AnnotationConfigWebApplicationContext;
import org.springframework.web.servlet.DispatcherServlet;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletRegistration;

public class ApplicationInitializer implements WebApplicationInitializer {


    @Override
    public void onStartup(ServletContext servletContext) throws ServletException {

        AnnotationConfigWebApplicationContext ctx = new AnnotationConfigWebApplicationContext();

        // Registers annotated configurations class
        ctx.register(AppConfig.class);

        // Sets ContextLoaderListener to servletContext
        servletContext.addListener(new ContextLoaderListener(ctx));

        // Passes servlet context to context instance
        ctx.setServletContext(servletContext);

        //Registers dispatch servlet and passes context instance
        ServletRegistration.Dynamic servlet = servletContext.addServlet("dispatcher", new DispatcherServlet(ctx));

        //Maps URL pattern
        servlet.addMapping("/");

        //Sets creation priority
        servlet.setLoadOnStartup(1);

    }
}
