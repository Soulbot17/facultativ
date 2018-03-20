package com.epam.webelecty.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;

import javax.annotation.Resource;

@Configuration
@EnableWebSecurity
@Import({DBConfig.class})
@ComponentScan(basePackages = {"com.epam.webelecty.services"})
public class SecurityConfig extends WebSecurityConfigurerAdapter {


    @Resource(name = "userDetailServiceImplementation")
    UserDetailsService userDetailService;



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

    @Autowired
    public void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.authenticationProvider(authenticationProvider());
    }

    @Bean
    public DaoAuthenticationProvider authenticationProvider() {
        DaoAuthenticationProvider authProvider
                = new DaoAuthenticationProvider();
        authProvider.setUserDetailsService(userDetailService);
        return authProvider;
    }
}
