package com.connection.database.service.impl;

import com.connection.database.entity.Transaction;
import com.connection.database.repository.jpa.TransactionJPARepository;
import com.connection.database.service.TransactionService;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

@Service
public class TransactionServiceImpl implements TransactionService {


    private static final List<Long> ACCOUNT_IDS = Arrays.asList(101L, 102L, 103L, 104L, 105L);
    private static final List<String> TRANSACTION_TYPES = Arrays.asList("deposit", "withdrawal");
    private static final List<String> STATUSES = Arrays.asList("success", "failed");
    private static final List<String> CURRENCIES = Arrays.asList("USD", "EUR", "GBP");
    private static final List<String> LOCATIONS = Arrays.asList("New York", "London", "Paris", "Berlin", "Tokyo");

    private final TransactionJPARepository transactionRepository;

    public TransactionServiceImpl(TransactionJPARepository transactionRepository) {
        this.transactionRepository = transactionRepository;
    }

    @Override
    public void insertMockData() {
        List<Transaction> transactions = new ArrayList<>();
        Random random = new Random();

        for (int i = 0; i < 7000000; i++) {
            Transaction transaction = new Transaction();
            transaction.setTransactionID(ACCOUNT_IDS.get(random.nextInt(ACCOUNT_IDS.size())));
            transaction.setTransactionDate(LocalDateTime.now().minusDays(random.nextInt(28)));
//            transaction.setAmount(BigDecimal.valueOf(random.nextDouble() * 50000).setScale(2, RoundingMode.HALF_UP));
            transaction.setTransactionType(TRANSACTION_TYPES.get(random.nextInt(TRANSACTION_TYPES.size())));
            transaction.setStatus(STATUSES.get(random.nextInt(STATUSES.size())));
            transaction.setCurrency(CURRENCIES.get(random.nextInt(CURRENCIES.size())));
            transaction.setLocation(LOCATIONS.get(random.nextInt(LOCATIONS.size())));

            transactions.add(transaction);

            if (transactions.size() == 10000) { // Batch insert 10,000 records
                transactionRepository.saveAll(transactions);
                transactions.clear();
                System.out.println("10,000 transactions inserted...");
            }
        }

        if (!transactions.isEmpty()) {
            transactionRepository.saveAll(transactions);
        }

        System.out.println("7 million transactions inserted.");
    }
}
