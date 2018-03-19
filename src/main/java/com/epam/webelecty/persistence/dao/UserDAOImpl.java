package com.epam.webelecty.persistence.dao;

import com.epam.webelecty.models.User;
import com.epam.webelecty.models.UserRole;
import com.epam.webelecty.persistence.database.ConnectionPool;
import lombok.extern.log4j.Log4j2;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@Log4j2
public class UserDAOImpl implements UserDAO {

    private ConnectionPool connectionPool;

    public UserDAOImpl(ConnectionPool connectionPool) {
        this.connectionPool = connectionPool;
    }


    public List<User> getAllUsers() {
        Connection connection = connectionPool.getConnection();
        String sql = "SELECT * FROM sql11226348.users";
        List<User> users = new ArrayList<>();
        try {
            PreparedStatement ps = connection.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                User user = parseUser(rs);
                users.add(user);
            }
        } catch (SQLException e) {
            log.error(e);
        }
        connectionPool.releaseConnection(connection);
        return users;
    }

    @Override
    public User getUserByEmail(String email) {
        Connection connection = connectionPool.getConnection();
        String sql = "SELECT * FROM sql11226348.users WHERE email=(?)";
        try {
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setString(1, email);
            ResultSet rs = ps.executeQuery();
            User user = parseUser(rs);
            if (user != null) return user;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public void updateUserInfo(int id, User user) {
        Connection connection = connectionPool.getConnection();
        String sql = "UPDATE sql11226348.users SET email=(?), pass=(?), name=(?), lastName=(?), role=(?) WHERE userId=(?)";
        try {
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setString(1, user.getEmail());
            ps.setString(2, user.getPassword());
            ps.setString(3, user.getName());
            ps.setString(4, user.getLastName());
            ps.setString(5, user.getRole().name());
            ps.setInt(6, id);
            ps.execute();
        } catch (SQLException e) {
            log.error(e);
        }
        connectionPool.releaseConnection(connection);
    }

    @Override
    public void removeUserById(int id) {
        Connection connection = connectionPool.getConnection();
        String sql = "DELETE FROM sql11226348.users WHERE userId=(?)";
        try {
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setInt(1, id);
            ps.execute();
        } catch (SQLException e) {
            log.error(e);
        }
        connectionPool.releaseConnection(connection);
    }

    @Override
    public void insert(User user) {
        Connection connection = connectionPool.getConnection();
        String sql = "INSERT INTO sql11226348.users(email, pass, role) VALUES(?, ?, ?)";
        try {
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setString(1, user.getEmail());
            ps.setString(2, user.getPassword());
            ps.setString(3, user.getRole().name());
            ps.executeUpdate();
            ps.close();
        } catch (SQLException e) {
            log.error(e);
        }
        connectionPool.releaseConnection(connection);
    }

    @Override
    public User getUserById(int id) {
        Connection connection = connectionPool.getConnection();
        String sql = "SELECT * FROM sql11226348.users WHERE userId=(?)";
        User user = null;
        try {
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                user = parseUser(rs);
            }
        } catch (SQLException e) {
            log.error(e);
        }
        connectionPool.releaseConnection(connection);
        return user;
    }

    private User parseUser(ResultSet rs) {
        User user = null;
        try {
            if (rs != null && rs.next()) {
                int id = rs.getInt("userId");
                String email = rs.getString("email");
                String pass = rs.getString("pass");
                String name = rs.getString("name");
                String lastName = rs.getString("lastName");
                UserRole role = rs.getString("role").toLowerCase().equals("tutor") ?
                        UserRole.TUTOR : UserRole.STUDENT;
                user = User.builder()
                        .userId(id)
                        .email(email)
                        .password(pass)
                        .name(name)
                        .lastName(lastName)
                        .role(role)
                        .build();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return user;
    }
}

