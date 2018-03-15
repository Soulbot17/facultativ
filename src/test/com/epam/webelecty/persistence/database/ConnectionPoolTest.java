package com.epam.webelecty.persistence.database;

import lombok.SneakyThrows;
import org.junit.Test;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;

public class ConnectionPoolTest {


    @Test
    @SneakyThrows

    public void getConnection1() {
        Connection connection = DriverManager.getConnection("jdbc:mysql://sql11.freemysqlhosting.net:3306/sql11226348",
                "sql11226348", "N7XThNApKx");
        ResultSet resultSet = connection.prepareStatement("select * from users").executeQuery();
        while (resultSet.next()) {
            System.out.println(resultSet.getString("name"));
            System.out.println(resultSet.getString("lastName"));
        }
    }
    @Test
    @SneakyThrows

    public void getConnection2() {
        ConnectionPool pool = ConnectionPool.getInstance();
        Connection connection = pool.getConnection();
        ResultSet resultSet = connection.prepareStatement("select * from users").executeQuery();
        while (resultSet.next()) {
            System.out.println(resultSet.getString("name"));
            System.out.println(resultSet.getString("lastName"));
        }
        pool.releaseConnection(connection);
    }

}