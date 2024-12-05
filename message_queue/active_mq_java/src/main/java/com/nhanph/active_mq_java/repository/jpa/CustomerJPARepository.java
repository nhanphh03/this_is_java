package com.nhanph.active_mq_java.repository.jpa;

import com.nhanph.active_mq_java.entity.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CustomerJPARepository extends JpaRepository<Customer, Long> {
}
