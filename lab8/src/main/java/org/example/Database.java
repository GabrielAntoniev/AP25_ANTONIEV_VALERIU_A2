package org.example;

import com.zaxxer.hikari.HikariDataSource;

import java.sql.Connection;
import java.sql.SQLException;

public class Database {
    private static final String URL = "jdbc:postgresql://localhost:5432/mydb";
    private static final String USER = "postgres";
    private static final String PASSWORD = "adminavg";
    private static HikariDataSource connectionPool = null;

    private Database() {}

    public static Connection getConnection() {
        if (connectionPool == null) {
            createConnectionPool();
        }
        try {
            return connectionPool.getConnection();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    private static synchronized void createConnectionPool() {
        if (connectionPool != null) return;
        connectionPool = new HikariDataSource();
        connectionPool.setJdbcUrl(URL);
        connectionPool.setUsername(USER);
        connectionPool.setPassword(PASSWORD);
        connectionPool.setAutoCommit(true);
    }

    public static void closeConnectionPool() {
        if (connectionPool != null) {
            connectionPool.close();
            connectionPool = null;
        }
    }
}