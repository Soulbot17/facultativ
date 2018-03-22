package com.epam.webelecty.models;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UserDTO{
    private int userId;
    private String email;
    private String password;
    private String name;
    private String lastName;
    private UserRole role;
    private String confirmPassword;

    public UserDTO(String email, String password, String confirmPassword, String name, String lastName, UserRole role) {
        this.email = email;
        this.password = password;
        this.name = name;
        this.lastName = lastName;
        this.role = role;
        this.confirmPassword = confirmPassword;
    }
}
