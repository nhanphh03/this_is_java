package com.connection.database.service;

import com.connection.database.entity.jpa.Transaction;

import java.util.List;

public interface TransactionService {
    List<Transaction> findAllByAccountId(Long accountId);
}
