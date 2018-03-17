package com.epam.webelecty.controllers;

import com.epam.webelecty.persistence.database.ConnectionPool;
import lombok.Setter;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;


@Setter
@Controller
public class TestController {
    private ConnectionPool connectionPool;

    @RequestMapping(value = "/", method = RequestMethod.GET)
    @ResponseBody
    public String getConnection(){
        return connectionPool.toString();
    }

}
