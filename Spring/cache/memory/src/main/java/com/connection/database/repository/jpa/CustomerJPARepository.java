package com.connection.database.repository.jpa;

import com.connection.database.entity.Customer;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CustomerJPARepository extends JpaRepository<Customer, Long> {

    @Cacheable(value = "name", key = "'queryValue' + #name")
    List<Customer> findByName(String name); //dựa vào entity để sinh ra query


    @Query(value = "SELECT a FROM Account a WHERE a.customerName = :name", nativeQuery = false)
    List<Customer> findByNameQuery(@Param("name") String name); //dựa vào entity và câu query để sinh ra query


    @Query(value = "SELECT * FROM account WHERE customer_name = :name", nativeQuery = true)
    List<Customer> findByNameQueryNativeQuery(@Param("name") String name); // chạy trực tếp câu query

    @Query(value = "SELECT u FROM Account u " +
            " WHERE u.customerName = :customerName AND " +
            " (u.email LIKE :email OR u.phoneNumber LIKE :phoneNumber) " +
            " ORDER BY u.accountId DESC",
            nativeQuery = false)
    List<Customer> findByNameQueryNativeQueryV(@Param("customerName") String customerName,
                                               @Param("email") String email,
                                               @Param("phoneNumber") String phoneNumber);



}
