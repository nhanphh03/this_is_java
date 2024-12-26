package com.cache.memory.config.datasource;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 *
 * @author nhanp
 */
public class ConnectJDBCBasicConnection {

    public static final String HOSTNAME = "localhost";
    public static final String PORT = "2003";
    public static final String DBNAME = "spring_boot";
    public static final String USERNAME = "nhanph";
    public static final String PASSWORD = "Nhan21..";

    public static Connection getConnection() {

        String connectionUrl = "jdbc:mysql://" + HOSTNAME + ":" + PORT + "/" + DBNAME;

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            return DriverManager.getConnection(connectionUrl, USERNAME, PASSWORD);
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace(System.out);
        }
        return null;
    }

}
