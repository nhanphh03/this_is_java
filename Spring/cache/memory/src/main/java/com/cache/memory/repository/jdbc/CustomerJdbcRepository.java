package com.cache.memory.repository.jdbc;

import com.cache.memory.config.datasource.ConnectJDBCBasicConnection;
import com.cache.memory.entity.Account;
import com.cache.memory.entity.Customer;
import com.cache.memory.repository.jpa.CustomerJPARepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;


@Repository
public class CustomerJdbcRepository {

    private static final Logger log = LoggerFactory.getLogger(CustomerJdbcRepository.class);
    private final JdbcTemplate jdbcTemplate;

    private final CustomerJPARepository customerRepository;

    public CustomerJdbcRepository(CustomerJPARepository customerRepository,
                               JdbcTemplate jdbcTemplate) {
        this.customerRepository = customerRepository;
        this.jdbcTemplate = jdbcTemplate;
    }

    public Integer getListCustomerV2() {
        String query = "select * from customer ";

        String query2 = "select * from customer c where c.name = 'Nhan'";

        List<Customer> list = new ArrayList<>();
        List<Customer> list2 = new ArrayList<>();

        Connection c = ConnectJDBCBasicConnection.getConnection();
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
                    list.add(new Customer(rs.getLong(1)
                            , rs.getString(2)
                            , rs.getInt(3)
                            , rs.getString(4)));
                }

                while (rs2.next()) { // TODO: Lấy kq lan 2
                    list2.add(new Customer(rs.getLong(1)
                            , rs.getString(2)
                            , rs.getInt(3)
                            , rs.getString(4)));
                }
                return list.size() + list2.size();
            } catch (Exception e) {
                try {
                    c.rollback();
                } catch (SQLException ex) {
                    throw new RuntimeException(ex);
                }
                CustomerJdbcRepository.log.error(e.getMessage());
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
                    throw new RuntimeException(ex);
                }
            }
        }
        return 0;
    }

    public Integer getListCustomer() {

        String query = "INSERT INTO customer (id, name, age) " +
                "VALUES (4, 'NHAN', 18)";


        String query2 = "INSERT INTO customer (id, name, age) " +
                "     VALUES (4, 'NHAN', 18)";

        List<Customer> list = new ArrayList<>();
        List<Customer> list2 = new ArrayList<>();

        Connection c = ConnectJDBCBasicConnection.getConnection();
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
                ps.executeUpdate(); // TODO: query trực tiếp xuống db lần 1

                ps2 = c.prepareStatement(query2);
                ps2.executeUpdate(); // TODO: query trực tiếp xuống db lần 2

//
//                while (rs2.next()) { // TODO: Lấy kq lan 2
//                    list2.add(new Customer(rs.getLong(1)
//                            , rs.getString(2)
//                            , rs.getInt(3)
//                            , rs.getString(4)));
//                }
                c.commit();
                return list2.size();

            } catch (Exception e) {
                try {
                    c.rollback();
                } catch (SQLException ex) {
                    throw new RuntimeException(ex);
                }
                CustomerJdbcRepository.log.error(e.getMessage());
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
                    throw new RuntimeException(ex);
                }
            }
        }
        return 0;
    }

    public List<Account> getListAccount() {
        String query = "select * from account ";
        List<Account> list = new ArrayList<>();
        Connection c = ConnectJDBCBasicConnection.getConnection();
        try {
            if (c != null) {
                c.setAutoCommit(false);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        ResultSet rs = null;
        PreparedStatement ps = null;
        if (c != null) {
            try {
                ps = c.prepareStatement(query);
                rs = ps.executeQuery();

                while (rs.next()) {
                    list.add(new Account(rs.getLong(1)
                            , rs.getString(2)
                            , rs.getString(3)
                            , rs.getString(4)));
                }
                return list;
            } catch (Exception e) {
                try {
                    c.rollback();
                } catch (SQLException ex) {
                    throw new RuntimeException(ex);
                }
                CustomerJdbcRepository.log.error(e.getMessage());
            }finally {
                try {
                    if (rs != null) {
                        rs.close();
                    }
                    if (ps != null) {
                        ps.close();
                    }
                    c.close();
                } catch (SQLException ex) {
                    throw new RuntimeException(ex);
                }
            }
        }
        return Collections.emptyList();
    }



}
