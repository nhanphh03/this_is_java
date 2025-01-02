package com.connection.database.controller;

import com.connection.database.dto.TransactionRequestToVNPay;
import com.connection.database.dto.TransactionResponseFromVNPay;
import com.connection.database.entity.Transaction;
import com.connection.database.service.CustomerService;
import com.connection.database.service.TransactionService;
import com.connection.database.service.impl.VNPAYServiceImpl;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigInteger;
import java.util.List;

@RestController
public class CustomerApi {

    private final CustomerService customerService;
    private final TransactionService transactionService;
    private final VNPAYServiceImpl vnpayService;

    public CustomerApi(CustomerService customerService,
                       VNPAYServiceImpl vnpayService,
                       TransactionService transactionService) {
        this.customerService = customerService;
        this.transactionService = transactionService;
        this.vnpayService = vnpayService;
    }

    @PostMapping(value = "/vnpay/request")
    public ResponseEntity<?> request(@RequestBody TransactionRequestToVNPay transactionRequestToVNPay) {
        TransactionRequestToVNPay transactionRequest =  new TransactionRequestToVNPay();
        transactionRequest.setAccountId(105L);
        transactionRequest.setAmount(BigInteger.valueOf(10500000));
        transactionRequest.setTransactionType("deposit");
        transactionRequest.setCurrency("VND");
        transactionRequest.setLocation("vn");
        return ResponseEntity.status(HttpStatus.OK).body(vnpayService.createVnPayPayment(transactionRequest));
    }

    @PostMapping(value = "/vnpay/response")
    public ResponseEntity<?> response(TransactionResponseFromVNPay transactionRequest) {
        System.out.println(transactionRequest);
        return ResponseEntity.status(HttpStatus.OK).body(customerService.countAllCustomers());
    }

    @PostMapping(value = "/transaction/account")
    public ResponseEntity<List<Transaction>> getAllTransactionByAccountID() {
        return ResponseEntity.status(HttpStatus.OK).body(transactionService.findAllByAccountID(105L));
    }
}
