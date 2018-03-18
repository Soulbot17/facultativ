package com.epam.webelecty.persistence.database.dao;

import com.epam.webelecty.models.User;
import com.epam.webelecty.models.UserRole;

public class UserDao {
    public User getByEmail(String email){
        if ("user@epam.com".equals(email)) return new User("user@epam.com", "user", "userName", "userLastName", UserRole.STUDENT);
        if ("tutor@epam.com".equals(email)) return new User("tutor@epam.com", "tutor", "tutorName", "tutorLastName", UserRole.TUTOR);
        return null;
    }
}
