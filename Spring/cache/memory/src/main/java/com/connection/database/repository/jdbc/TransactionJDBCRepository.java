package com.connection.database.repository.jdbc;

import com.connection.database.config.datasource.ConnectJDBCBasicConnection;
import com.connection.database.entity.Transaction;
import org.springframework.stereotype.Repository;

import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;


@Repository
public class TransactionJDBCRepository {

    public List<Transaction> findAllByAccountID(Long accountID){
        List<Transaction> transactions = new ArrayList<>();

        Connection connection = ConnectJDBCBasicConnection.getConnection();







        return transactions;
    }
}
