package com.epam.webelecty.persistence.dao;

import com.epam.webelecty.models.User;
import com.epam.webelecty.models.UserRole;
import com.epam.webelecty.persistence.dao.exceptions.users.NoUserFoundException;
import com.epam.webelecty.persistence.dao.exceptions.users.UserNotCreatedException;
import com.epam.webelecty.persistence.database.ConnectionPool;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.sql.*;
import java.util.HashSet;
import java.util.Set;

@Log4j2
@Component
public class UserDAO implements DAO<User> {

    @Value("${db.name}")
    private String databaseName;

    private ConnectionPool connectionPool;

    @Autowired
    public UserDAO(ConnectionPool connectionPool) {
        this.connectionPool = connectionPool;
    }


    @Override
    public Set<User> getAllEntries() {
        Connection connection = connectionPool.getConnection();
        String sql = String.format("SELECT userId, email, pass, name, lastName, role FROM %s.users", databaseName);
        Set<User> userSet = new HashSet<>();
        try (ResultSet rs = connection.prepareStatement(sql).executeQuery()) {
            while (rs.next()) {
                User user = parseUser(rs);
                if (user != null) userSet.add(user);
            }
        } catch (SQLException e) {
            log.error(e);
            throw new NoUserFoundException(e);
        } finally {
            connectionPool.releaseConnection(connection);
        }
        return userSet;
    }

    public User getUserByEmail(String email) {
        Connection connection = connectionPool.getConnection();
        String sql = String.format("SELECT userId, email, pass, name, lastName, role FROM %s.users WHERE email='%s'",
                databaseName, email);
        try (ResultSet rs = connection.prepareStatement(sql).executeQuery()) {
            if (rs.next()) return parseUser(rs);
        } catch (SQLException e) {
            log.error(e);
            throw new NoUserFoundException(e);
        } finally {
            connectionPool.releaseConnection(connection);
        }
        return null;
    }

    @Override
    public User updateEntry(User entry) {
        String sql = String.format("UPDATE %s.users SET email='%s', pass='%s', name='%s', lastName='%s', role='%s' WHERE userId=%d",
                databaseName, entry.getEmail(), entry.getPassword(), entry.getName(),
                entry.getLastName(), entry.getRole().name(), entry.getUserId());
        executeSqlStatement(connectionPool, sql);
        return getById(entry.getUserId());
    }

    @Override
    public void removeById(int id) {
        String sql = String.format("DELETE FROM %s.users WHERE userId=%d", databaseName, id);
        executeSqlStatement(connectionPool, sql);
    }

    @Override
    public User insert(User entry) {
        String sql = String.format("INSERT INTO %s.users(email, pass, role, name, lastName) VALUES('%s', '%s', '%s', '%s', '%s')",
                databaseName, entry.getEmail(), entry.getPassword(), entry.getRole().name()
                , entry.getName() == null ? "Empty" : entry.getName(), entry.getLastName() == null ? "Empty" : entry.getLastName());
        Connection connection = connectionPool.getConnection();
        User user = null;
        try (PreparedStatement ps = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            ps.executeUpdate();
            try (ResultSet generatedKeys = ps.getGeneratedKeys()) {
                if (generatedKeys.next()) user = getById(generatedKeys.getInt(1));
            }
        } catch (SQLException e) {
            log.error(e);
            throw new UserNotCreatedException("User was not created");
        } finally {
            connectionPool.releaseConnection(connection);
        }
        return user;
    }

    @Override
    public User getById(int id) {
        Connection connection = connectionPool.getConnection();
        String sql = String.format("SELECT userId, email, pass, name, lastName, role FROM %s.users WHERE userId=%d",
                databaseName, id);
        User user = null;
        try (ResultSet rs = connection.prepareStatement(sql).executeQuery()) {
            if (rs.next()) user = parseUser(rs);
        } catch (SQLException e) {
            log.error(e);
            throw new NoUserFoundException(e);
        } finally {
            connectionPool.releaseConnection(connection);
        }
        return user;
    }

    static User parseUser(ResultSet rs) {
        User user;
        try {
            int id = rs.getInt("userId");
            String email = rs.getString("email");
            String pass = rs.getString("pass");
            String name = rs.getString("name");
            String lastName = rs.getString("lastName");
            UserRole role = "tutor".equalsIgnoreCase(rs.getString("role")) ?
                    UserRole.TUTOR : UserRole.STUDENT;
            user = User.builder()
                    .userId(id)
                    .email(email)
                    .password(pass)
                    .name(name)
                    .lastName(lastName)
                    .role(role)
                    .build();
        } catch (SQLException e) {
            log.error(e);
            throw new NoUserFoundException(e);
        }
        return user;
    }
}