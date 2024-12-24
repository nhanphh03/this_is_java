package com.cache.memory.controller;

import com.cache.memory.service.VNPayService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CustomerApi {


    private final VNPayService vnPayService;

    public CustomerApi(VNPayService vnPayService) {
        this.vnPayService = vnPayService;
    }

    @PostMapping(value = "/")
    public ResponseEntity<?> getCustomerByName(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size) {
        return ResponseEntity.status(HttpStatus.OK).body(vnPayService.createVnPayPayment());
    }
}
