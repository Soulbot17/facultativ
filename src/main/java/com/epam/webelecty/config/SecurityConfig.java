package com.epam.webelecty.config;

import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;


@Configuration
@EnableWebSecurity
@ComponentScan(basePackages = {"com.epam.webelecty"})
@Log4j2
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    @Qualifier("userDetailServiceImplementation")
    private UserDetailsService userDetailService;

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests()
                .antMatchers("/registration", "/login").access("permitAll()")
                .antMatchers("/user_tutor").access("hasAnyRole('ROLE_TUTOR')")
                .antMatchers("/user_student").access("hasAnyRole('ROLE_STUDENT')")
                .antMatchers("/*").access("hasAnyRole('ROLE_STUDENT', 'ROLE_TUTOR')")
                .and().formLogin()
                .loginPage("/login")
                .failureUrl("/login?error")
                .defaultSuccessUrl("/user")
                .usernameParameter("email").passwordParameter("password")
                .and().logout()
                .logoutSuccessUrl("/login?logout")
                .and().csrf();
    }

    @Autowired
    public void configure(AuthenticationManagerBuilder auth) {
        try {
            auth.authenticationProvider(authenticationProvider());
        }catch (Exception e){
            log.error(e);
        }
    }

    @Bean
    public DaoAuthenticationProvider authenticationProvider() {
        DaoAuthenticationProvider authProvider
                = new DaoAuthenticationProvider();
        authProvider.setUserDetailsService(userDetailService);
        return authProvider;
    }

}
