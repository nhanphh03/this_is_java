package com.connection.database.config.datasource.hibernate;

import org.hibernate.SessionFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class HibernateConfig {

    private static SessionFactory sessionFactory;

    @Bean
    public SessionFactory getSessionFactory() {
        if (sessionFactory == null) {
            try {
                org.hibernate.cfg.Configuration configuration = new org.hibernate.cfg.Configuration();
                configuration.configure();
                sessionFactory = configuration.buildSessionFactory();
            } catch (Exception e) {
                System.out.println("Error creating session factory: " + e);
                throw new ExceptionInInitializerError(e);
            }
        }
        return sessionFactory;
    }

    public void closeSessionFactory() {
        if (sessionFactory != null) {
            sessionFactory.close();
        }
    }
}
