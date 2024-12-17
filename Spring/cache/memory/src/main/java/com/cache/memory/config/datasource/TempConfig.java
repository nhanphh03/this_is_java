//package com.cache.memory.config.datasource;
//
//import com.zaxxer.hikari.HikariConfig;
//import com.zaxxer.hikari.HikariDataSource;
//import jakarta.persistence.EntityManagerFactory;
//import lombok.RequiredArgsConstructor;
//import org.springframework.beans.factory.annotation.Qualifier;
//import org.springframework.boot.autoconfigure.jdbc.DataSourceProperties;
//import org.springframework.boot.context.properties.ConfigurationProperties;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.core.env.Environment;
//import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
//import org.springframework.jdbc.core.JdbcTemplate;
//import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
//import org.springframework.orm.jpa.JpaTransactionManager;
//import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
//import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
//import org.springframework.transaction.PlatformTransactionManager;
//
//import javax.sql.DataSource;
//import java.util.Properties;
//
//@Configuration
//@EnableJpaRepositories( basePackages = "com.cache.memory.repository.jpa")
//@RequiredArgsConstructor
//public class DataSourceConfig extends HikariConfig {
//
//    private final Environment env;
//
//    @Bean
//    @ConfigurationProperties("app.datasource-mysql")
//    public DataSourceProperties dataSourceProperties() {
//        return new DataSourceProperties();
//    }
//
//    @Bean
//    @ConfigurationProperties("app.datasource-mysql.hikari")
//    public DataSource dataSource(@Qualifier("dataSourceProperties") DataSourceProperties properties) {
//        return properties.initializeDataSourceBuilder().type(HikariDataSource.class).build();
//    }
//
//    @Bean
//    public JdbcTemplate jdbcTemplate(@Qualifier("dataSource") DataSource properties) {
//        return new JdbcTemplate(properties);
//    }
//
//    @Bean
//    public NamedParameterJdbcTemplate namedParameterJdbcTemplate(
//            @Qualifier("dataSource") DataSource dataSource) {
//        return new NamedParameterJdbcTemplate(dataSource);
//    }
//
//    @Bean
//    public LocalContainerEntityManagerFactoryBean entityManagerFactory(@Qualifier("dataSource") DataSource builder) {
//        LocalContainerEntityManagerFactoryBean factoryBean = new LocalContainerEntityManagerFactoryBean();
//        factoryBean.setDataSource(builder);
//
//        factoryBean.setPackagesToScan("com.cache");
//        factoryBean.setPersistenceUnitName("memory");
//
//        HibernateJpaVendorAdapter vendorAdapter = new HibernateJpaVendorAdapter();
//        vendorAdapter.setGenerateDdl(true);
//        vendorAdapter.setShowSql(true);
//
//        factoryBean.setJpaVendorAdapter(vendorAdapter);
//        factoryBean.setJpaProperties(jpaProperties(env));
//
//        return factoryBean;
//    }
//
//    @Bean
//    public PlatformTransactionManager transactionManager(
//            @Qualifier("entityManagerFactory") EntityManagerFactory entityManagerFactory) {
//        JpaTransactionManager transactionManager = new JpaTransactionManager();
//        transactionManager.setEntityManagerFactory(entityManagerFactory);
//        return transactionManager;
//    }
//
//    private Properties jpaProperties(Environment environment) {
//        Properties properties = new Properties();
//        properties.put("hibernate.dialect", environment.getProperty("spring.jpa.hibernate.ddl-auto"));
//        properties.put("hibernate.format_sql", environment.getProperty("spring.jpa.properties.hibernate.format_sql"));
//        properties.put("hibernate.show-sql", environment.getProperty("spring.jpa.properties.hibernate.format_sql"));
//        properties.put("hibernate.generate_statistics", environment.getProperty("spring.jpa.properties.hibernate.generate_statistics"));
//        return properties;
//    }
//
//
//}
//
//server:
//port: 2308
//servlet:
//context-path: /memory/
//
//spring:
//application:
//name: memory
//  #JPA
//datasource:
//username: nhanph
//password: Nhan21..
//jpa:
//hibernate:
//naming:
//physical-strategy: org.hibernate.boot.model.naming.CamelCaseToUnderscoresNamingStrategy
//ddl-auto: none
//properties:
//hibernate:
//generate_statistics: false
//dialect: false
//format_sql: true
//type: trace
//show-sql: true
//
//        #    main:
//        #      allow-bean-definition-overriding: true
//
//app:
//datasource-mysql:
//url: jdbc:mysql://localhost:2003/spring_boot
//driverClassName: com.mysql.cj.jdbc.Driver
//username: ${spring.datasource.username}
//password: ${spring.datasource.password}
//type: com.zaxxer.hikari.HikariDataSource
//hikari:
//schema: spring_boot
//auto-commit: true
//connection-timeout: 10000
//maximum-pool: 3000
//max-lifetime: 1100000
//minimum-idle: 300
//cache:
//redis:
//host: localhost
//port: 6379
//        #    userRedisCluster: true
//        #    enable: true
//        #    username: 'nhanph'
//        #    password: Nhan21..

