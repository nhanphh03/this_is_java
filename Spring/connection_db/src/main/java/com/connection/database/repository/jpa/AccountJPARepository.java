package com.connection.database.repository.jpa;

import com.connection.database.entity.Account;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AccountJPARepository extends JpaRepository<Account, Long> {
    @Cacheable(value = "name", key = "'queryValue' + #name")
    List<Account> findByName(String name); //dựa vào entity để sinh ra query


    @Query(value = "SELECT a FROM Account a WHERE a.name = :name", nativeQuery = false)
    List<Account> findByNameQuery(@Param("name") String name); //dựa vào entity và câu query để sinh ra query


    @Query(value = "SELECT * FROM account WHERE name = :name", nativeQuery = true)
    List<Account> findByNameQueryNativeQuery(@Param("name") String name); // chạy trực tếp câu query

    @Query(value = "SELECT u FROM Account u " +
            " WHERE u.name = :name AND " +
            " (u.email LIKE :email OR u.age = :age) " +
            " ORDER BY u.id DESC")

    List<Account> findByNameQueryNativeQueryV(@Param("name") String name,
                                               @Param("email") String email,
                                               @Param("age") int age);
}
