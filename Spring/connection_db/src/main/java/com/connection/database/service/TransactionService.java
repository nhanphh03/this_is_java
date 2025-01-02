package com.connection.database.service;

import com.connection.database.entity.jpa.Transaction;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface TransactionService {
    List<Transaction> findAllByAccountId( Pageable pageable);
    List<Transaction> findAllByAccountIdPage(Pageable pageable);
}
