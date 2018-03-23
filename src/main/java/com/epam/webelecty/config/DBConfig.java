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
import java.util.stream.Collectors;
import java.util.stream.Stream;

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
    @Value("${db.InitDBScript}")
    String sqlBaseInit;
    @Value("${db.FillDBScript}")
    String sqlFillDB;

    @Bean
    public ConnectionPool connectionPool() {
        return new ConnectionPool(
                driverName, url, user, password, poolSize);
    }

    @PostConstruct
    public void runScript() {
        DAO dao = new DAO() {
            @Override
            public Set getAllEntries() {
                throw new UnsupportedOperationException("This method shouldn't use in anonymous class");
            }

            @Override
            public Object updateEntry(Object entry) {
                throw new UnsupportedOperationException("This method shouldn't use in anonymous class");
            }

            @Override
            public void removeById(int id) {
                throw new UnsupportedOperationException("This method shouldn't use in anonymous class");
            }

            @Override
            public Object insert(Object entry) {
                throw new UnsupportedOperationException("This method shouldn't use in anonymous class");
            }

            @Override
            public Object getById(int id) {
                throw new UnsupportedOperationException("This method shouldn't use in anonymous class");
            }
        };
        dao.executeSqlStatement(connectionPool(), getSQLFromFile(sqlBaseInit));
        dao.executeSqlStatement(connectionPool(), getSQLFromFile(sqlFillDB));
    }

    private String getSQLFromFile(String file) {
        String sql = "";
        try (Stream<String> stream = Files.lines(Paths.get(file))) {
            sql = stream.collect(Collectors.joining());
        } catch (IOException e) {
            log.error(e);
        }
        return sql;
    }
}
