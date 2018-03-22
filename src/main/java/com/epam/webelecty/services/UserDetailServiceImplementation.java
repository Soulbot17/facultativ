package com.epam.webelecty.services;

import com.epam.webelecty.models.User;
import com.epam.webelecty.models.UserRole;
import com.epam.webelecty.persistence.dao.UserDAO;
import lombok.Setter;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;

@Service
@Log4j2
public class UserDetailServiceImplementation implements UserDetailsService {

    private UserDAO userDao;

    @Autowired
    public UserDetailServiceImplementation(UserDAO userDao) {
        this.userDao = userDao;
    }

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        User user = userDao.getUserByEmail(email);
        log.error(user);
        Set<GrantedAuthority> grantedAuthorities = new HashSet<>();
        if (UserRole.TUTOR == user.getRole()) {
            grantedAuthorities.add(new SimpleGrantedAuthority("ROLE_TUTOR"));
        } else {
            grantedAuthorities.add(new SimpleGrantedAuthority("ROLE_STUDENT"));
        }
        return new org.springframework.security.core.userdetails.User(user.getEmail(), user.getPassword(), grantedAuthorities);
    }
}
