package com.connection.database.service;


import com.connection.database.dto.TransactionRequestToVNPay;

public interface VNPayService {
    String createVnPayPayment(TransactionRequestToVNPay transactionRequest);

    String callUrlAndGetResponse(String url);
}
