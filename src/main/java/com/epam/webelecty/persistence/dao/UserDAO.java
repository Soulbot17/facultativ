package com.epam.webelecty.persistence.dao;

import com.epam.webelecty.models.User;

public interface UserDAO {
    User getUserByEmail(String email);
    void updateUserInfo(int id, User user);
    void removeUserById(int id);
    void insert(User user);
    User getUserById(int id);
}
