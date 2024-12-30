package com.connection.database.repository.jpa;

import com.connection.database.entity.Transaction;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TransactionJPARepository extends JpaRepository<Transaction, Long> {
}
