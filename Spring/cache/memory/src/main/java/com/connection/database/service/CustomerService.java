package com.connection.database.service;

import com.connection.database.entity.Customer;
import org.springframework.data.domain.Page;

import java.util.List;

public interface CustomerService {

    List<Customer> getCustomers(String name);

    Page<Customer> getAllCustomers(int page, int size);

    List<Customer> getAllCustomers();

    Integer countAllCustomers();
}
