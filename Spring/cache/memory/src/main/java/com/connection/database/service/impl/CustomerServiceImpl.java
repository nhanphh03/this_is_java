package com.connection.database.service.impl;


import com.connection.database.config.redis.CachePage;
import com.connection.database.entity.Customer;
import com.connection.database.repository.jdbc.CustomerJdbcRepository;
import com.connection.database.repository.jpa.AccountJPARepository;
import com.connection.database.repository.jpa.CustomerJPARepository;
import com.connection.database.service.CustomerService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class CustomerServiceImpl implements CustomerService {

    private final CustomerJPARepository customerRepository;

    private final AccountJPARepository accountJPARepository;

    private final CustomerJdbcRepository customerJdbcRepository;

    private final JdbcTemplate jdbcTemplate;

    private final RedisTemplate<String, Object> redisTemplate;

    public CustomerServiceImpl(CustomerJPARepository customerRepository,
                                CustomerJdbcRepository customerJdbcRepository,
                               AccountJPARepository accountJPARepository,
                               RedisTemplate<String,
                               Object> redisTemplate,
                               JdbcTemplate jdbcTemplate) {
        this.customerRepository = customerRepository;
        this.customerJdbcRepository = customerJdbcRepository;
        this.accountJPARepository = accountJPARepository;
        this.jdbcTemplate = jdbcTemplate;
        this.redisTemplate = redisTemplate;
    }

    @Override
    @Transactional
    public List<Customer> getAllCustomers() {
        return null;
//        return customerJdbcRepository.getListCustomer();
    }

    @Override
    public Integer countAllCustomers() {

//    Integer a = customerRepository.findAll().size();
//    Integer b = accountJPARepository.findAll().size();
////
//        Integer a = customerJdbcRepository.getListCustomer();
//        Integer b = customerJdbcRepository.getListAccount().size();
        

        return 2;
    }

    @Transactional
    public List<Customer> getCustomers(String name) {
        return customerRepository.findByName(name);
    }

    @CachePage(value = "allAccounts")
    public Page<Customer> getAllCustomers(int page, int size) {
        String cacheKey = "users:" + page + ":" + size;

        if (Boolean.TRUE.equals(redisTemplate.hasKey(cacheKey))) {
            Object cachedData = redisTemplate.opsForValue().get(cacheKey);
            if (cachedData instanceof Page) {
                return (Page<Customer>) cachedData;
            }
        }

        Pageable pageable = PageRequest.of(page, size);
        Page<Customer> customerPage = customerRepository.findAll(pageable);
        redisTemplate.opsForValue().set(cacheKey, customerPage);

        return customerPage;
    }


}
