package com.connection.database.controller;

import com.connection.database.entity.jpa.Transaction;
import com.connection.database.service.TransactionService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class TransactionController {

    private final TransactionService transactionService;
    public TransactionController(TransactionService transactionService){
        this.transactionService = transactionService;
    }

    @PostMapping(value = "/getTransaction")
    public ResponseEntity<List<Transaction>> getAccountName() {
        return ResponseEntity.status(HttpStatus.OK).body(transactionService.findAllByAccountId(105L));
    }
}
