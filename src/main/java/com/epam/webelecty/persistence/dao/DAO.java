package com.epam.webelecty.persistence.dao;

import com.epam.webelecty.persistence.database.ConnectionPool;
import lombok.extern.log4j.Log4j2;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Set;

public interface DAO<P> {
    Set<P> getAllEntries();
    P updateEntry(P entry);
    void removeById(int id);
    P insert(P entry);
    P getById(int id);

    default void executeSqlStatement(ConnectionPool connectionPool, String sql) {
        Connection connection = connectionPool.getConnection();
        try (PreparedStatement ps = connection.prepareStatement(sql)){
            ps.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            connectionPool.releaseConnection(connection);
        }
    }
}
