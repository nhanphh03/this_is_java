package com.connection.database.service;

import com.connection.database.entity.jpa.Account;
import org.springframework.data.domain.Page;

import java.util.List;

public interface AccountService {

    List<Account> getAccounts(String name);

    Page<Account> getAllAccounts(int page, int size);

    List<Account> getAllAccounts();

    Integer countAllAccounts();
}
