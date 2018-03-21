package com.epam.webelecty.models;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.beans.Transient;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class User {
    private int userId;
    private String email;
    private String password;
    private String name;
    private String lastName;
    private UserRole role;

    private String confirmPassword;

    public User(String email, String password, String name, String lastName, UserRole role) {
        this.email = email;
        this.password = password;
        this.name = name;
        this.lastName = lastName;
        this.role = role;
    }
}
