package com.cache.memory.controller;

import com.cache.memory.entity.Customer;
import com.cache.memory.service.CustomerService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class CustomerApi {

    private final CustomerService customerService;

    public CustomerApi(CustomerService customerService) {
        this.customerService = customerService;
    }

    @GetMapping("/")
    private String getCustomerByName() {
        String name = "NHAN";

        List<Customer> customers = customerService.getCustomers(name);
        System.out.println(customers);
        return name;
    }
}
