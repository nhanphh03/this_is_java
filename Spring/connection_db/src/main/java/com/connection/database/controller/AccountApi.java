package com.connection.database.controller;

import com.connection.database.service.AccountService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AccountApi {

    private final AccountService accountService;

    public AccountApi(AccountService accountService) {
        this.accountService = accountService;
    }

    @PostMapping(value = "/")
    public ResponseEntity<?> getAccountName() {
        return ResponseEntity.status(HttpStatus.OK).body(accountService.countAllAccounts());
    }
}
