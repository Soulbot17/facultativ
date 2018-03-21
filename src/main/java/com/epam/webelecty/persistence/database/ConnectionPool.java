package com.epam.webelecty.persistence.database;

import lombok.extern.log4j.Log4j2;

import javax.annotation.PreDestroy;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

@Log4j2
public class ConnectionPool {
    private BlockingQueue<Connection> connectionQueue;
    private String driverName;
    private String url;
    private String user;
    private String password;
    private int poolSize;

    public Connection getConnection() {
        Connection connection = null;
        try {
            connection = connectionQueue.take();
        } catch (InterruptedException e) {
            log.error("Free connection waiting interrupted. Returned `null` connection", e);
        }
        return connection;
    }

    @PreDestroy
    public void dispose() throws SQLException {
        Connection connection;
        while ((connection = connectionQueue.poll()) != null) {
            if (!connection.getAutoCommit()) {
                connection.commit();
            }
            connection.close();
        }
    }

    public void releaseConnection(Connection connection) {
        try {
            if (!connection.isClosed()) {
                if (!connectionQueue.offer(connection)) {
                    log.debug("Connection not added. "
                            + "Possible `leakage` of connections");
                }
            } else {
                log.debug("Trying to release closed connection. "
                        + "Possible `leakage` of connections");
            }
        } catch (SQLException e) {
            log.error("SQLException at connection isClosed() checking. Connection not added", e);
        }
    }

    public ConnectionPool(String driverName, String url, String user, String password, int poolSize) {
        this.driverName = driverName;
        this.url = url;
        this.user = user;
        this.password = password;
        this.poolSize = poolSize;
        connectionQueue = new ArrayBlockingQueue<>(poolSize);
        try {
            Class.forName(driverName);
        } catch (ClassNotFoundException e) {
            log.debug(e);
        }
        for (int i = 0; i < poolSize; i++) {
            try {
                connectionQueue.offer(DriverManager.getConnection(url, user, password));
            } catch (SQLException e) {
                log.error(e);
            }
        }
    }
}
