package com.connection.database.config.datasource;

import lombok.Getter;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import java.sql.Connection;

public class HibernateConfig {

    @Getter
    private static final HibernateConfig instance = new HibernateConfig();
    private static SessionFactory sessionFactory;

    private HibernateConfig() {}

    public SessionFactory getSessionFactory() {
        if (sessionFactory == null) {
            try {
                Configuration configuration = new Configuration();
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
