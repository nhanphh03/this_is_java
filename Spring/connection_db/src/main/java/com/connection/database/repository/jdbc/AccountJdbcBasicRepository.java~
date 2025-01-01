package com.connection.database.repository.jdbc;

import com.connection.database.config.datasource.ConnectJDBCBasicConnection;
import com.connection.database.entity.Account;
import lombok.extern.log4j.Log4j;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static com.connection.database.config.datasource.ConnectJDBCBasicConnection.*;

@Repository
public class AccountJdbcBasicRepository {

    private static final Logger log = LogManager.getLogger(AccountJdbcBasicRepository.class);

    private final ConnectJDBCBasicConnection connection;

    public AccountJdbcBasicRepository(ConnectJDBCBasicConnection connection) {
        this.connection = connection;
    }
    

    public Integer getListAccountV2() {
        String query = "select * from cusotmer ";

        String query2 = "select * from account c where c.name = 'Nhan'";

        List<Account> list = new ArrayList<>();
        List<Account> list2 = new ArrayList<>();

        Connection c = connection.getConnection();
        setAutoCommit(c, true);

        ResultSet rs = null;
        ResultSet rs2 = null;

        PreparedStatement ps = null;
        PreparedStatement ps2 = null;

        if (c != null) {
            try {
                ps = c.prepareStatement(query);
                rs = ps.executeQuery(); // TODO: query trực tiếp xuống db lần 1

                ps2 = c.prepareStatement(query2);
                rs2 = ps2.executeQuery(); // TODO: query trực tiếp xuống db lần 2

                while (rs.next()) {// TODO: Lấy kq lan 1
                    list.add(new Account(rs.getLong(1)
                            , rs.getString(2)
                            , rs.getString(3)
                            , rs.getInt(4)));
                }

                while (rs2.next()) { // TODO: Lấy kq lan 2
                    list2.add(new Account(rs.getLong(1)
                            , rs.getString(2)
                            , rs.getString(3)
                            , rs.getInt(4)));
                }
                return list.size() + list2.size();
            } catch (Exception e) {
                rollbackConnection(c, log);
            }finally {
                closeResultSet(rs2, log);
                closeResultSet(rs, log);
                closePreparedStatement(ps2, log);
                closePreparedStatement(ps, log);
                closeConnection(c, log);
            }
        }
        return 0;
    }

    public Integer getListAccountV3() {

        String query = "INSERT INTO account (id, name, age) " +
                " VALUES (4, 'NHAN', 18)";


        String query2 = "INSERT INTO account (id, name, age) " +
                " VALUES (4, 'NHAN', 18)";

        List<Account> list2 = new ArrayList<>();

        Connection connection = this.connection.getConnection();
        setAutoCommit(connection, true);

        ResultSet rs2 = null;

        PreparedStatement ps = null;
        PreparedStatement ps2 = null;

        if (connection != null) {
            try {
                ps = connection.prepareStatement(query);
                ps.executeUpdate(); // TODO: query trực tiếp xuống db lần 1

                ps2 = connection.prepareStatement(query2);
                rs2 = ps2.executeQuery(); // TODO: query trực tiếp xuống db lần 2

                while (rs2.next()) { // TODO: Lấy kq lan 2
                    list2.add(new Account(rs2.getLong(1)
                            , rs2.getString(2)
                            , rs2.getString(3)
                            , rs2.getInt(4)));
                }
                connection.commit();
                return list2.size();

            } catch (Exception e) {
                rollbackConnection(connection, log);
            }finally {
                closeResultSet(rs2, log);
                closePreparedStatement(ps2, log);
                closePreparedStatement(ps, log);
                closeConnection(connection, log);
            }
        }
        return 0;
    }

    public List<Account> getListAccount() {
        String query = "select * from account ";
        List<Account> list = new ArrayList<>();
        Connection connection = this.connection.getConnection();
        setAutoCommit(connection, true);
        ResultSet rs = null;
        PreparedStatement ps = null;
        if (connection != null) {
            try {
                ps = connection.prepareStatement(query);
                rs = ps.executeQuery();

                while (rs.next()) {
                    list.add(new Account(rs.getLong(1)
                            , rs.getString(2)
                            , rs.getString(3)
                            , rs.getInt(4)));
                }
                return list;
            } catch (Exception e) {
                rollbackConnection(connection, log);
            }finally {
                closeResultSet(rs, log);
                closePreparedStatement(ps, log);
            }
        }
        return Collections.emptyList();
    }



}
