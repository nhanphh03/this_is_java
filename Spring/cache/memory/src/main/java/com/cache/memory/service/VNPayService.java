package com.cache.memory.service;


public interface VNPayService {
    String createVnPayPayment();

    String callUrlAndGetResponse(String url);
}
