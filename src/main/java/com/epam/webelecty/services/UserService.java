package com.epam.webelecty.services;

import com.epam.webelecty.models.User;
import com.epam.webelecty.models.UserDTO;

public interface UserService {

    User getUserByEmail(String email);

    User getRoleByEmail();

    public void register(UserDTO user);
}
