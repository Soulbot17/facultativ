package com.epam.webelecty.services;

import com.epam.webelecty.models.User;
import com.epam.webelecty.models.UserDTO;
import com.epam.webelecty.models.UserRole;
import com.epam.webelecty.persistence.dao.UserDAO;
import com.epam.webelecty.services.exeptions.EmailIsUsedException;
import com.epam.webelecty.services.exeptions.RegisterDataException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImplementation  implements UserService{
    @Autowired
    UserDAO userDAO;
    @Autowired
    BCryptPasswordEncoder encoder;



    @Override
    public User getUserByEmail(String email) {
        return userDAO.getUserByEmail(email);
    }

    @Override
    public User getRoleByEmail() {
        String email = SecurityContextHolder.getContext().getAuthentication().getName();
        return userDAO.getUserByEmail(email);
    }

    @Override
    public void register(UserDTO userDTO) {
        if ("".equals(userDTO.getName())
                || "".equals(userDTO.getLastName())
                || "".equals(userDTO.getLastName())
                || "".equals(userDTO.getEmail())
                || userDTO.getPassword().length()<5
                || !userDTO.getPassword().equals(userDTO.getConfirmPassword())){
            throw new RegisterDataException();
        }
        if(this.getUserByEmail(userDTO.getEmail())!=null){
            throw new EmailIsUsedException();
        }
        userDTO.setPassword(encoder.encode(userDTO.getPassword()));
        userDTO.setRole(UserRole.STUDENT);
        userDAO.insert(customUserDTOToUser(userDTO));
    }

    private User customUserDTOToUser(UserDTO userDTO){
        User user=new User(
                userDTO.getEmail(),
                userDTO.getPassword(),
                userDTO.getName(),
                userDTO.getLastName(),
                userDTO.getRole());
        return user;
    }
}
