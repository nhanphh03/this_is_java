package com.connection.database.service.impl;

import com.connection.database.entity.jpa.Transaction;
import com.connection.database.repository.jdbc.TransactionJDBCRepository;
import com.connection.database.repository.jpa.TransactionJPARepository;
import com.connection.database.service.TransactionService;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class TransactionServiceImpl implements TransactionService {

    private final TransactionJDBCRepository transactionJDBCRepository;
    private final TransactionJPARepository transactionJPARepository;

    public TransactionServiceImpl(TransactionJDBCRepository transactionJDBCRepository,
                                  TransactionJPARepository transactionJPARepository) {
        this.transactionJDBCRepository = transactionJDBCRepository;
        this.transactionJPARepository = transactionJPARepository;
    }

    @Override
    public List<Transaction> findAllByAccountId( Pageable pageable) {
        return transactionJDBCRepository.findTransactionsByAccountIdJDBCTemplate(105L, pageable.getPageSize(), pageable.getOffset());
    }

    @Override
    public List<Transaction> findAllByAccountIdPage(Pageable pageable) {
        return transactionJPARepository.findTransactionByAccountID(105L, pageable).getContent();
    }
}
