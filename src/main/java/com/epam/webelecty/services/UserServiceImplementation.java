package com.epam.webelecty.services;

import com.epam.webelecty.models.User;
import com.epam.webelecty.persistence.dao.UserDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImplementation  implements UserService{
    @Autowired
    UserDAO userDAO;

    @Override
    public void insert(User user) {

    }

    @Override
    public User getUserByEmail(String email) {
        return null;
    }

    @Override
    public User getRoleByEmail() {
        String email = SecurityContextHolder.getContext().getAuthentication().getName();
        return userDAO.getUserByEmail(email);
    }
}
