package com.cache.redis.service;


import com.cache.redis.entity.Customer;
import com.cache.redis.repository.jpa.CustomerJPARepository;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CustomerService {

    private final com.cache.redis.repository.jpa.CustomerJPARepository customerRepository;

    public CustomerService(CustomerJPARepository customerRepository) {
        this.customerRepository = customerRepository;
    }

    public List<Customer> getCustomers(String name) {
        return customerRepository.findByName(name);
    }


    @Cacheable(value = "allAccounts",key = "'accounts:' + #page + ':' + #size")
    public Page<Customer> getAll(int page, int size) {
        Pageable pageable = PageRequest.of(page, size);
        return customerRepository.findAll(pageable);
    }
}
