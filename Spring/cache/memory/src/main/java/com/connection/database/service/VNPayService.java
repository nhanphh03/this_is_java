package com.connection.database.service;


public interface VNPayService {
    String createVnPayPayment();

    String callUrlAndGetResponse(String url);
}
