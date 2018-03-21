package com.epam.webelecty.services;

import com.epam.webelecty.models.User;

public interface UserService {

    void insert(User user);

    User getUserByEmail(String email);

    User getRoleByEmail();
}
