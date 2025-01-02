package com.connection.database.repository.jpa;

import com.connection.database.entity.jpa.Transaction;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TransactionJPARepository extends JpaRepository<Transaction, Long> {

    Page<Transaction> findTransactionByAccountID(Long accountID, Pageable pageable);

}
