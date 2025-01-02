package com.connection.database.repository.jpa;

import com.connection.database.entity.Transaction;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TransactionJPARepository extends JpaRepository<Transaction, Long> {
    List<Transaction> findAllByAccountID(Long accountID);
}
