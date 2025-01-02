package com.connection.database.service.impl;

import com.connection.database.entity.jpa.Transaction;
import com.connection.database.repository.jdbc.TransactionJDBCRepository;
import com.connection.database.service.TransactionService;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class TransactionServiceImpl implements TransactionService {

    private final TransactionJDBCRepository transactionJDBCRepository;

    public TransactionServiceImpl(TransactionJDBCRepository transactionJDBCRepository) {
        this.transactionJDBCRepository = transactionJDBCRepository;
    }

    @Override
    public List<Transaction> findAllByAccountId(Long accountId) {
        return transactionJDBCRepository.findTransactionsByAccountIdJDBCTemplate(accountId);
    }
}
