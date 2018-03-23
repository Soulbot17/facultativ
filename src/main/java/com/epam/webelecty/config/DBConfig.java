package com.epam.webelecty.config;

import com.epam.webelecty.persistence.dao.DAO;
import com.epam.webelecty.persistence.database.ConnectionPool;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

import javax.annotation.PostConstruct;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Set;

@Log4j2
@Configuration
@PropertySource("classpath:dbh2.properties")
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

    @PostConstruct
    public void runScript() {
        String sqlBaseInit = getSQLFromFile("src/main/resources/db/dataBaseInitH2.sql");
        String sqlFillDB = getSQLFromFile("src/main/resources/db/fillDBWithTestDataH2.sql");
        DAO dao = new DAO() {
            @Override
            public Set getAllEntries() {
                return null;
            }

            @Override
            public Object updateEntry(Object entry) {
                return null;
            }

            @Override
            public void removeById(int id) {

            }

            @Override
            public Object insert(Object entry) {
                return null;
            }

            @Override
            public Object getById(int id) {
                return null;
            }
        };
        dao.executeSqlStatement(connectionPool(), sqlBaseInit);
        dao.executeSqlStatement(connectionPool(), sqlFillDB);
    }

    private String getSQLFromFile(String file) {
        StringBuilder sql = new StringBuilder("");
        try {
            Files.lines(Paths.get(file)).forEach(sql::append);
        } catch (IOException e) {
            log.error(e);
        }
        return sql.toString();
    }
}
