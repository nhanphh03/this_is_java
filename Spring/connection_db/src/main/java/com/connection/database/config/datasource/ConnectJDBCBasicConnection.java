package com.connection.database.config.datasource;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.sql.*;

/**
 *
 * @author nhanp
 * Cấu hình một connection đơn giản tới database
 */

@Configuration
public class ConnectJDBCBasicConnection {

    private static final Logger log = LogManager.getLogger(ConnectJDBCBasicConnection.class);

    @Value("${spring.datasource.driver-class-name}")
    private String driverConnection;

    @Value("${spring.datasource.url}")
    private String url;

    @Value("${spring.datasource.password}")
    private String password;

    @Value("${spring.datasource.username}")
    private String username;

    public ConnectJDBCBasicConnection(){}

    @Bean(name = "jdbcBasicRepository")
    public Connection getConnection() {
        try {
            Class.forName(driverConnection);
            return DriverManager.getConnection(url, username, password);
        } catch (ClassNotFoundException | SQLException e) {
            log.error("{}", e.getMessage());
        }
        return null;
    }


    public static void setAutoCommit(Connection connection, boolean autoCommit){
        try {
            if (connection != null) {
                connection.setAutoCommit(autoCommit);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public static void closePreparedStatement(PreparedStatement preparedStatement, Logger logger){
        try {
            if (preparedStatement != null) {
                preparedStatement.close();
            }
        } catch (SQLException e) {
            logger.error("Error closing PreparedStatement {}", e.getMessage());
        }
    }

    public static void closeResultSet(ResultSet resultSet, Logger logger){
        try {
            if (resultSet != null) {
                resultSet.close();
            }
        } catch (SQLException e) {
            logger.error("Error closing ResultSet {}", e.getMessage());
        }
    }

    public static void closeConnection(Connection connection, Logger logger){
        try {
            if (connection != null) {
                connection.close();
            }
        } catch (SQLException e) {
            logger.error("Error closing Connection {}", e.getMessage());
        }
    }

    public static void rollbackConnection(Connection connection, Logger logger){
        try {
            if (connection != null) {
                connection.rollback();
            }
        } catch (SQLException e) {
            logger.error("Error rollback Connection {}", e.getMessage());
        }
    }

}
