package com.connection.database.service;

import com.connection.database.entity.Transaction;

import java.util.List;

public interface TransactionService {
    void insertMockData();

    List<Transaction> findAllByAccountID(Long accountID);
}
