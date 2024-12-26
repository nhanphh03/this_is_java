package com.cache.memory.repository.jpa;

import com.cache.memory.entity.Account;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AccountJPARepository extends JpaRepository<Account, Long> {
}
