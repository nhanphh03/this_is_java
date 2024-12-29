package com.connection.database.config.datasource;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;

import javax.sql.DataSource;

/**
 *
 * @author nhanp
 */

@Configuration
@EnableJpaRepositories(basePackages = "com.connection.database.repository.jpa")
public class DataSourceConfigHikariPool extends HikariConfig {

    @Value("${spring.datasource.driver-class-name}")
    private String driver;

    @Value("${spring.datasource.url}")
    private String url;

    @Value("${spring.datasource.password}")
    private String password;

    @Value("${spring.datasource.username}")
    private String username;

    @Bean
    public DataSource demoDatasource() {
        HikariConfig hikariConfig = new HikariConfig();
        hikariConfig.setDriverClassName(driver);
        hikariConfig.setJdbcUrl(url);
        hikariConfig.setUsername(username);
        hikariConfig.setPassword(password);
        hikariConfig.addDataSourceProperty("cachePrepStmts", "true");
        hikariConfig.addDataSourceProperty("prepStmtCacheSize", "250");
        hikariConfig.addDataSourceProperty("prepStmtCacheSqlLimit", "2048");
        hikariConfig.setMaximumPoolSize(10);
        return new HikariDataSource(hikariConfig);
    }

     /*

     NamedParameterJdbcTemplate và JdbcTemplate đều là các lớp trong Spring Framework dùng để
      tương tác với cơ sở dữ liệu thông qua JDBC.

     #JdbcTemplate
        Sử dụng dấu chấm hỏi ? làm placeholder cho tham số.
        Các tham số được truyền vào thông qua mảng hoặc danh sách (theo thứ tự).
        Khó hiểu khi có nhiều tham số vì phải đảm bảo thứ tự chính xác.
         Dùng khi câu SQL đơn giản và số lượng tham số ít, hoặc khi hiệu suất là ưu tiên cao
         (vì JdbcTemplate có thể nhanh hơn một chút trong một số trường hợp do không phải xử lý tên tham số).

        #NamedParameterJdbcTemplate
        Sử dụng tên tham số (named parameters) trong câu SQL.
        Các tham số được truyền vào thông qua một đối tượng Map hoặc SqlParameterSource.
        Dễ đọc và dễ duy trì hơn vì sử dụng tên tham số thay vì dựa vào thứ tự.
        Dùng khi câu SQL phức tạp hoặc có nhiều tham số, giúp code dễ đọc và bảo trì hơn.
     */

    @Bean
    public NamedParameterJdbcTemplate demoNamedParameterJdbcTemplate(
            @Qualifier("demoDatasource") DataSource dataSource) {
        return new NamedParameterJdbcTemplate(dataSource);
    }

    @Bean
    public JdbcTemplate demoJdbcTemplate(
            @Qualifier("demoDatasource") DataSource dataSource) {
        return new JdbcTemplate(dataSource);
    }



}
