package com.cache.memory.repository.jdbc;

import com.cache.memory.entity.Customer;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.Map;

@Repository
public class CustomerHibernateRepository {

    @Autowired
    @Qualifier("demoJdbcTemplate")
    private NamedParameterJdbcTemplate jdbcTemplate;


    // Implement methods for CRUD operations

    public void addCustomer(Customer customer) {
        String query = "INSERT INTO customer (id, name, age) " +
                "VALUES (:id, 'NHAN', 18)";

//        MapSqlParameterSource newQuery = new MapSqlParameterSource();
//        newQuery.addValue("id", customer.getId());

        Map<String, Long> paramMap = new HashMap<>();
        paramMap.put("id", customer.getId());

        jdbcTemplate.update(query, paramMap);


    }

}
