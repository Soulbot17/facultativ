package com.epam.webelecty.persistence.database;

import org.junit.Assert;
import org.junit.Test;
import java.sql.Connection;
import java.sql.SQLException;

public class ConnectionPoolTest {
    private static final int EXPECTED_POOL_SIZE = 5;

    @Test
    public void testConnectionPoolGetInstance() throws SQLException {
        ConnectionPool pool = ConnectionPool.getInstance();

        Assert.assertEquals(EXPECTED_POOL_SIZE, pool.connectionQueue.size());

    }

    @Test
    public void testGetConnectionFromPool() throws SQLException {
        ConnectionPool pool = ConnectionPool.getInstance();
        Connection connection = pool.getConnection();

        Assert.assertTrue("connection wasn`t opened", !connection.isClosed());
        Assert.assertEquals(EXPECTED_POOL_SIZE - 1, pool.connectionQueue.size());

        safeCloseConnection(connection);
    }

    @Test
    public void testReleaseConnectionToPool() throws SQLException {
        ConnectionPool pool = ConnectionPool.getInstance();
        Connection connection = pool.getConnection();
        pool.releaseConnection(connection);

        Assert.assertEquals(EXPECTED_POOL_SIZE, pool.connectionQueue.size());

        safeCloseConnection(connection);
    }

    private static void safeCloseConnection(Connection connection) throws SQLException {
        if (!connection.getAutoCommit()) {
            connection.commit();
        }
        connection.close();
    }
}