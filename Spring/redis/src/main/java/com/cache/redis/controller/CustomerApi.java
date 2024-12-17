package com.cache.redis.controller;

import com.cache.redis.entity.Customer;
import com.cache.redis.service.CustomerService;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class CustomerApi {

    private final CustomerService customerService;

    public CustomerApi(CustomerService customerService) {
        this.customerService = customerService;
    }

    @GetMapping(value="/all", produces= MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Object> getCustomerByName() {
        Page<Customer> customers = customerService.getAll(1, 10);
        return ResponseEntity.status(HttpStatus.OK).body(customers);
    }
}
