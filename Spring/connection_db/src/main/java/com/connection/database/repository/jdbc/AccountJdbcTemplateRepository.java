package com.connection.database.repository.jdbc;

import com.connection.database.entity.Account;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class AccountJdbcTemplateRepository {

    private final JdbcTemplate jdbcTemplate;
    private final NamedParameterJdbcTemplate namedJdbcTemplate;

    public AccountJdbcTemplateRepository(@Qualifier("demoNamedParameterJdbcTemplate")
                                         NamedParameterJdbcTemplate namedJdbcTemplate,
                                         @Qualifier("demoJdbcTemplate") JdbcTemplate jdbcTemplate) {
        this.namedJdbcTemplate = namedJdbcTemplate;
        this.jdbcTemplate = jdbcTemplate;
    }

    public void addAccountJDBCTemplateBasic(Account account) {
        String query = "INSERT INTO account (id, name, email, age) " +
                "VALUES (?, ?, ?, ?)";
        jdbcTemplate.update(query, account.getId(), account.getName(), account.getAge());
    }

    public void addAccountNamedJDBCTemplate(Account account) {
        String query = "INSERT INTO account (id, name, email, age) " +
                "VALUES (:id, :name, :email, :age)";
        MapSqlParameterSource param = new MapSqlParameterSource();
        param.addValue("id", account.getId());
        param.addValue("name", account.getName());
        param.addValue("email", account.getEmail());
        param.addValue("age", account.getAge());
        namedJdbcTemplate.update(query, param);
    }

    public List<Account> getAllAccountJDBCTemplate() {
        String query = "SELECT * FROM account";
        return namedJdbcTemplate.queryForList(query, new MapSqlParameterSource(), Account.class);
    }

}
