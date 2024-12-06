package com.cache.memory.repository.jpa;

import com.cache.memory.entity.Customer;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CustomerJPARepository extends JpaRepository<Customer, Long> {

    @Cacheable(value = "name", key = "'queryValue' + #name")
    List<Customer> findByName(String name);
}
