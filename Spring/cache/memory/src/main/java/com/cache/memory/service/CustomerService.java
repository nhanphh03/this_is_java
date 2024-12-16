package com.cache.memory.service;


import com.cache.memory.config.dto.PageWrapper;
import com.cache.memory.config.redis.CachePage;
import com.cache.memory.entity.Customer;
import com.cache.memory.repository.jpa.CustomerJPARepository;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;
import com.fasterxml.jackson.core.type.TypeReference;
import java.util.List;


@Service
public class CustomerService {

    private final CustomerJPARepository customerRepository;

    private final RedisTemplate<String, Object> redisTemplate;

    public CustomerService(CustomerJPARepository customerRepository,
                           @Qualifier("redisTemplate") RedisTemplate<String, Object> redisTemplate) {
        this.customerRepository = customerRepository;
        this.redisTemplate = redisTemplate;
    }

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
