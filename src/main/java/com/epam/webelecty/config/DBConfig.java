package com.epam.webelecty.config;

import com.epam.webelecty.persistence.database.ConnectionPool;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

@Configuration
@PropertySource("classpath:db.properties")
public class DBConfig {

    @Value("${db.driver}")
    private String driverName;
    @Value("${db.user}")
    private String user;
    @Value("${db.url}")
    private String url;
    @Value("${db.password}")
    private String password;
    @Value("${db.poolsize}")
    private int poolSize;

    @Bean
    public ConnectionPool connectionPool() {
        return new ConnectionPool(
                driverName, url, user, password, poolSize);
    }


}
