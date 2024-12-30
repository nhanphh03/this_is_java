package com.connection.database.repository.jdbc;

import com.connection.database.entity.Transaction;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

@Repository
public class TransactionJDBCRepository {

    private static final List<Long> ACCOUNT_IDS = Arrays.asList(101L, 102L, 103L, 104L, 105L);
    private static final List<String> TRANSACTION_TYPES = Arrays.asList("deposit", "withdrawal");
    private static final List<String> STATUSES = Arrays.asList("success", "failed");
    private static final List<String> CURRENCIES = Arrays.asList("USD", "EUR", "GBP");
    private static final List<String> LOCATIONS = Arrays.asList("New York", "London", "Paris", "Berlin", "Tokyo");

    public void insertMockData() {

    }

}
