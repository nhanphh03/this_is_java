package com.connection.database.repository.jdbc;

import com.connection.database.config.datasource.ConnectJDBCBasicConnection;
import com.connection.database.entity.Account;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;


public class AccountJdbcBasicRepository {

    private static final Logger log = LoggerFactory.getLogger(AccountJdbcBasicRepository.class);

    private final ConnectJDBCBasicConnection connection;

    public AccountJdbcBasicRepository(ConnectJDBCBasicConnection connection) {
        this.connection = connection;
    }
    

    public Integer getListaccountV2() {
        String query = "select * from cusotmer ";

        String query2 = "select * from account c where c.name = 'Nhan'";

        List<Account> list = new ArrayList<>();
        List<Account> list2 = new ArrayList<>();

        Connection c = connection.getConnection();
        try {
            if (c != null) {
                c.setAutoCommit(false);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

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
                try {
                    c.rollback();
                } catch (SQLException ex) {
                    log.error("Error closing resources", ex);
                }
                AccountJdbcBasicRepository.log.error(e.getMessage());
            }finally {
                try {
                    if (rs != null) {
                        rs.close();
                    }
                    if (rs2 != null) {
                        rs2.close();
                    }
                    if (ps != null) {
                        ps.close();
                    }
                    if (ps2 != null) {
                        ps2.close();
                    }
                    c.close();
                } catch (SQLException ex) {
                    log.error("Error closing resources", ex);
                }
            }
        }
        return 0;
    }

    public Integer getListaccount() {

        String query = "INSERT INTO account (id, name, age) " +
                "VALUES (4, 'NHAN', 18)";


        String query2 = "INSERT INTO account (id, name, age) " +
                "     VALUES (4, 'NHAN', 18)";

        List<Account> list = new ArrayList<>();
        List<Account> list2 = new ArrayList<>();

        Connection connection = this.connection.getConnection();
        try {
            if (connection != null) {
                connection.setAutoCommit(false);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        ResultSet rs = null;
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
                try {
                    connection.rollback();
                } catch (SQLException ex) {
                    log.error("Error closing resources", ex);
                }
                AccountJdbcBasicRepository.log.error(e.getMessage());
            }finally {
                try {
                    if (rs2 != null) {
                        rs2.close();
                    }
                    if (ps != null) {
                        ps.close();
                    }
                    if (ps2 != null) {
                        ps2.close();
                    }
                    connection.close();
                } catch (SQLException ex) {
                    log.error("Error closing resources", ex);
                }
            }
        }
        return 0;
    }

    public List<Account> getListAccount() {
        String query = "select * from account ";
        List<Account> list = new ArrayList<>();
            Connection connection = this.connection.getConnection();
        try {
            if (connection != null) {
                connection.setAutoCommit(false);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
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
                try {
                    connection.rollback();
                } catch (SQLException ex) {
                    throw new RuntimeException(ex);
                }
                AccountJdbcBasicRepository.log.error(e.getMessage());
            }finally {
                try {
                    if (rs != null) {
                        rs.close();
                    }
                    if (ps != null) {
                        ps.close();
                    }
                    connection.close();
                } catch (SQLException ex) {
                    log.error("Error closing resources", ex);
                }
            }
        }
        return Collections.emptyList();
    }



}
