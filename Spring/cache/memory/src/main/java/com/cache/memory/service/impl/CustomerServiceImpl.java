package com.cache.memory.service.impl;


import com.cache.memory.config.redis.CachePage;
import com.cache.memory.entity.Customer;
import com.cache.memory.repository.jpa.CustomerJPARepository;
import com.cache.memory.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;


@Service
public class CustomerServiceImpl implements CustomerService {

    private final CustomerJPARepository customerRepository;

    private final RedisTemplate<String, Object> redisTemplate;

    public CustomerServiceImpl(CustomerJPARepository customerRepository,
                               @Qualifier("redisTemplate") RedisTemplate<String, Object> redisTemplate) {
        this.customerRepository = customerRepository;
        this.redisTemplate = redisTemplate;
    }

    @Autowired
    @Transactional
    public List<Customer> getCustomers(String name) {
        return customerRepository.findByName(name);
    }

    @Autowired
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
