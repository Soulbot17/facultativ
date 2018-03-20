package com.epam.webelecty.config;

import com.epam.webelecty.persistence.dao.UserDAO;
import com.epam.webelecty.persistence.dao.UserDAOImpl;
import com.epam.webelecty.persistence.database.ConnectionPool;
import com.epam.webelecty.services.UserDetailServiceImplementation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.context.annotation.PropertySource;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;

@Configuration
@EnableWebSecurity
@Import({DBConfig.class})
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    ConnectionPool connectionPool;



    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests()
                .antMatchers("/index").access("permitAll()")
                .antMatchers("/*").access("hasAnyRole('ROLE_ADMIN', 'ROLE_USER')")
                .and().formLogin()
                .loginPage("/index")
                .failureUrl("/index?error")
                .defaultSuccessUrl("/user", true)
                .usernameParameter("email").passwordParameter("password")
                .and().logout()
                .logoutSuccessUrl("/index?logout")
                .deleteCookies("JSESSIONID")
                .and().csrf();
    }

    @Bean
    public UserDetailServiceImplementation userDetailServiceImplementation(){
        UserDetailServiceImplementation userDetailServiceImplementation = new UserDetailServiceImplementation();
        userDetailServiceImplementation.setUserDao(userDAO());
        return userDetailServiceImplementation;
    }

    @Autowired
    public void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.authenticationProvider(authenticationProvider());
    }

    @Bean
    public DaoAuthenticationProvider authenticationProvider() {
        DaoAuthenticationProvider authProvider
                = new DaoAuthenticationProvider();
        authProvider.setUserDetailsService(userDetailServiceImplementation());
        return authProvider;
    }

    @Bean
    public UserDAO userDAO(){
        return new UserDAOImpl(connectionPool);
    }


}
