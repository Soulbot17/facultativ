package com.epam.webelecty.persistence.database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

public class ConnectionPool {
//    private static ConnectionPool instance;
    private BlockingQueue<Connection> connectionQueue;
    private String driverName;
    private String url;
    private String user;
    private String password;
    private int poolSize;

    private ConnectionPool(String driverName, String url, String user, String password, int poolSize) {
        this.driverName = driverName;
        this.url = url;
        this.user = user;
        this.password = password;
        this.poolSize = poolSize;
        connectionQueue = new ArrayBlockingQueue<>(poolSize);
        for (int i = 0; i < poolSize; i++) {
            try {
                connectionQueue.offer(DriverManager.getConnection(url, user, password));
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    public Connection getConnection() {
        Connection connection = null;
        try {
            connection = connectionQueue.take();
        } catch (InterruptedException e) {
            //"Free connection waiting interrupted.
            // Returned `null` connection", e
        }
        return connection;
    }

//    public static void dispose() throws SQLException {
//        if (instance != null) {
//            instance.clearConnectionQueue();
//            instance = null;
//        }
//    }


    public void releaseConnection(Connection connection) {
        try {
            if (!connection.isClosed()) {
                if (!connectionQueue.offer(connection)) {
                    //"Connection not added. Possible `leakage` of
                    // connections"
                }
            } else {
                //"Trying to release closed connection. Possible
                // `leakage` of connections"
            }
        } catch (SQLException e) {
            //"SQLException at conection isClosed () checking.
            // Connection not added", e
        }
    }

    private void clearConnectionQueue() throws SQLException {
        Connection connection;
        while ((connection = connectionQueue.poll()) != null) {
            /* see java.sql.Connection#close () javadoc */
            if (!connection.getAutoCommit()) {
                connection.commit();
            }
            connection.close();
        }
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("ConnectionPool{");
        sb.append("connectionQueue=").append(connectionQueue);
        sb.append(", driverName='").append(driverName).append('\'');
        sb.append(", url='").append(url).append('\'');
        sb.append(", user='").append(user).append('\'');
        sb.append(", password='").append(password).append('\'');
        sb.append(", poolSize=").append(poolSize);
        sb.append('}');
        return sb.toString();
    }
}
