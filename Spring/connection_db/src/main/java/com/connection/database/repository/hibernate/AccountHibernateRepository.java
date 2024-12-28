package com.connection.database.repository.hibernate;

import com.connection.database.config.datasource.HibernateConfig;
import com.connection.database.entity.Account;
import lombok.extern.slf4j.Slf4j;
import org.hibernate.Session;
import org.hibernate.Transaction;

@Slf4j
public class AccountHibernateRepository {

    private final HibernateConfig hibernateConfig;

    public AccountHibernateRepository(HibernateConfig hibernateConfig) {
        this.hibernateConfig = hibernateConfig;
    }

    public Long createAccount(Account account) {
        Long accountId;
        Session session = hibernateConfig.getSessionFactory().getCurrentSession();
        Transaction transaction = session.beginTransaction();
        try {
            accountId = (Long) session.save(account);
            transaction.commit();
            AccountHibernateRepository.log.info("account created successfully");
        } catch (Exception e) {
            transaction.rollback();
            throw new RuntimeException(e);
        }
        return accountId;
    }

}
