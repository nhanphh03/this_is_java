package com.connection.database.repository.jdbc;

import com.connection.database.entity.Customer;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class CustomerHibernateRepository {

    @Autowired
    @Qualifier("demoNamedParameterJdbcTemplate")
    private NamedParameterJdbcTemplate jdbcTemplate;


    // Implement methods for CRUD operations

    public void addCustomer(Customer customer) {
        String query = "INSERT INTO customer (id, name, age) " +
                "VALUES (:id, 'NHAN', 18)";
        MapSqlParameterSource param = new MapSqlParameterSource();
        param.addValue("id", customer.getId());
        jdbcTemplate.update(query, param);

    }

}
