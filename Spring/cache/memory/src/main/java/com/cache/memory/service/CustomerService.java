package com.cache.memory.service;


import com.cache.memory.entity.Customer;
import com.cache.memory.repository.jpa.CustomerJPARepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CustomerService {

    private final CustomerJPARepository customerRepository;

    public CustomerService(CustomerJPARepository customerRepository) {
        this.customerRepository = customerRepository;
    }

    public List<Customer> getCustomers(String name) {
        return customerRepository.findByName(name);
    }
}
