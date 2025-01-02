package com.connection.database.controller;

import com.connection.database.dto.TransactionRequest;
import com.connection.database.entity.jpa.Transaction;
import com.connection.database.service.TransactionService;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;


@RestController(value = "transaction")
public class TransactionController {

    private final TransactionService transactionService;
    public TransactionController(TransactionService transactionService){
        this.transactionService = transactionService;
    }

    @PostMapping(value = "/getTransaction/page")
    public ResponseEntity<List<Transaction>> getAccountNameJDBC(Pageable pageable) {
        return ResponseEntity.status(HttpStatus.OK).body(transactionService.findAllByAccountIdPage(pageable));
    }

    @PostMapping(value = "/getTransaction/jdbc")
    public ResponseEntity<List<Transaction>> getAccountNameJPA(Pageable pageable) {
        return ResponseEntity.status(HttpStatus.OK).body(transactionService.findAllByAccountId(pageable));
    }
}
