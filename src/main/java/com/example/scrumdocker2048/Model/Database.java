package com.example.scrumdocker2048.Model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * Database class for managing database connections
 */
public class Database {
    /**
     * Host address of the database
     */
    private String host = "localhost";
    /**
     * User for database connection
     */
    private String user = "2048";
    /**
     * Name of the database
     */
    private String db = "2048";
    /**
     * Password for database connection
     */
    private String password = "2048";
    /**
     * Type of database driver
     */
    private String driverType = "mysql";
    /**
     * Port number for database connection
     */
    private int port = 4306;
    /**
     * Connection instance to the database
     */
    private static Connection connection = null;

    /**
     * Loads the database driver class when the class is loaded
     */
    static {
        try {
            Class.forName("com.mysql.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    /**
     * Private constructor for establishing database connection
     */
    private Database() {
        try {
            Connection c = DriverManager.getConnection(
                    "jdbc:" + driverType + "://" + host + ":" + port + "/" + db + "?useSSL=false",
                    user,
                    password
            );
            connection = c;
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * Returns a connection instance to the database
     *
     * @return Connection instance to the database
     */
    public static Connection getConnection() {
        if (connection == null) {
            new Database();
        }
        return connection;
    }
}