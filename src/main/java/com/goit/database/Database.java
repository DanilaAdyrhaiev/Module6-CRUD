package com.goit.database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Database {
    public static final String dbUrl = "jdbc:mysql://127.0.0.1:3306/module6";
    public static final String dbUser = "root";
    public static final String dbPass = "Asosl@n2002";
    private Connection connection;
    private static Database instance;
    private Database(){

    }

    public static synchronized Database getInstance() {
        if (instance == null) {
            instance = new Database();
        }
        return instance;
    }

    public Connection getConnection() throws SQLException {
        if (connection == null)
            connection = DriverManager.getConnection(dbUrl, dbUser, dbPass);
        return connection;
    }
}
