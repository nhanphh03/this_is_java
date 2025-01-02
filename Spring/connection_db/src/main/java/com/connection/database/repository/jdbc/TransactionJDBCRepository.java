package com.connection.database.repository.jdbc;

import com.connection.database.config.datasource.basic_jdbc.ConnectJDBCBasicConnection;
import com.connection.database.entity.jpa.Transaction;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import static com.connection.database.config.datasource.basic_jdbc.ConnectJDBCBasicConnection.*;

@Repository
public class TransactionJDBCRepository {

    private static final Logger log = LogManager.getLogger(TransactionJDBCRepository.class);

    private final ConnectJDBCBasicConnection connection;
    private final NamedParameterJdbcTemplate jdbcTemplate;
    private final JdbcTemplate jdbcTemplateBasic;

    public TransactionJDBCRepository(ConnectJDBCBasicConnection connection,
                                     @Qualifier("demoNamedParameterJdbcTemplate") NamedParameterJdbcTemplate jdbcTemplate,
                                     @Qualifier("demoJdbcTemplate") JdbcTemplate jdbcTemplateBasic) {
        this.connection = connection;
        this.jdbcTemplate = jdbcTemplate;
        this.jdbcTemplateBasic = jdbcTemplateBasic;
    }

    public List<Transaction> findTransactionsByAccountId(Long accountId) {
        String sql = "SELECT * FROM transaction WHERE account_id = ? LIMIT 5000";
        List<Transaction> transactionList = new ArrayList<>();

        Connection c = null;
        PreparedStatement ps = null;
        ResultSet rs = null;

        try {
            c = connection.getConnection();
            if (c == null || c.isClosed()) {
                log.error("Connection is null or closed");
                return transactionList;
            }

            ps = c.prepareStatement(sql);
            ps.setLong(1, accountId);
            rs = ps.executeQuery();

            while (rs.next()) {
                transactionList.add(new Transaction(
                        rs.getLong(1),
                        rs.getLong(2),
                        rs.getTimestamp(3),
                        rs.getInt(4),
                        rs.getString(5),
                        rs.getString(6),
                        rs.getString(7),
                        rs.getString(8)
                ));
            }
        } catch (SQLException e) {
            log.error("Error during query execution", e);
            rollbackConnection(c, log);
        } finally {
            closeResultSet(rs, log);
            closePreparedStatement(ps, log);
            closeConnection(c, log);
        }

        return transactionList;
    }

    public List<Transaction> findTransactionsByAccountIdJDBCTemplate(Long accountId) {
//        String sql = "SELECT * FROM transaction WHERE account_id = :accountId ";
        String sql = "SELECT * FROM transaction WHERE account_id = :accountId LIMIT 10000 OFFSET 0;";
        MapSqlParameterSource parameterSource = new MapSqlParameterSource();
        parameterSource.addValue("accountId", accountId);

        return jdbcTemplate.query(sql, parameterSource, (rs, rowNum) -> new Transaction(
                rs.getLong("transaction_id"),
                rs.getLong("account_id"),
                rs.getTimestamp("transaction_date"),
                rs.getInt("amount"),
                rs.getString("transaction_type"),
                rs.getString("status"),
                rs.getString("currency"),
                rs.getString("location")
        ));
    }
    public List<Transaction> findTransactionsByAccountIdJDBCTemplateBasic(Long accountId) {
        String sql = "SELECT * FROM transaction WHERE account_id = ? LIMIT 5000 OFFSET 0;";
        return jdbcTemplateBasic.query(sql, new Object[]{accountId}, (rs, rowNum) -> new Transaction(
                rs.getLong("id"),
                rs.getLong("account_id"),
                rs.getTimestamp("created_at"),
                rs.getInt("status"),
                rs.getString("description"),
                rs.getString("type"),
                rs.getString("currency"),
                rs.getString("amount")
        ));




}
