package com.cache.memory.repository.jdbc;

import com.cache.memory.entity.Customer;
import com.cache.memory.repository.jpa.CustomerJPARepository;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
public class CustomerJdbcRepository {

    private final JdbcTemplate jdbcTemplate;

    private final CustomerJPARepository customerRepository;

    public CustomerJdbcRepository(CustomerJPARepository customerRepository,
                               JdbcTemplate jdbcTemplate) {
        this.customerRepository = customerRepository;
        this.jdbcTemplate = jdbcTemplate;
    }



}
