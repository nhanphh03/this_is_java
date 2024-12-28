package com.connection.database.config.datasource;

import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.beans.factory.annotation.Value;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 *
 * @author nhanp
 * Cấu hình một connection đơn giản tới database
 */

@Configurable
@Log4j2
public class ConnectJDBCBasicConnection {

    @Value("${spring.datasource.driver-class-name}")
    private static String driverConnection;

    @Value("${spring.datasource.url}")
    private static String url;

    @Value("${spring.datasource.password}")
    private static String password;

    @Value("${spring.datasource.username}")
    private static String username;

    private ConnectJDBCBasicConnection(){}

    public static Connection getConnection() {
        try {
            Class.forName(driverConnection);
            return DriverManager.getConnection(url, username, password);
        } catch (ClassNotFoundException | SQLException e) {
            log.error("{}", e.getMessage());
        }
        return null;
    }

}
