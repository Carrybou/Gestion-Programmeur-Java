package org.example.config;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public final class DatabaseManager {

    private static final String DB_HOST = System.getenv().getOrDefault("POSTGRES_HOST", "localhost");
    private static final String DB_PORT = System.getenv().getOrDefault("POSTGRES_PORT", "5432");
    private static final String DB_NAME = System.getenv().getOrDefault("POSTGRES_DB", "prog_bd");
    private static final String DB_USER = System.getenv().getOrDefault("POSTGRES_USER", "app_user");
    private static final String DB_PASSWORD = System.getenv().getOrDefault("POSTGRES_PASSWORD", "app_password");

    private static final String JDBC_URL =
            "jdbc:postgresql://" + DB_HOST + ":" + DB_PORT + "/" + DB_NAME;

    private DatabaseManager() {
    }

    public static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(JDBC_URL, DB_USER, DB_PASSWORD);
    }
}

