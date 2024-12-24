package com.cache.memory.mapper.impl;

import com.cache.memory.dto.AccountDTO;
import com.cache.memory.entity.Account;
import com.cache.memory.mapper.AccountMapper;

public class AccountMapperImpl implements AccountMapper {
    @Override
    public AccountDTO toDto(Account account) {

        AccountDTO accountDTO = new AccountDTO();
        accountDTO.setAccountId(account.getAccountId());
        accountDTO.setCustomerName(account.getCustomerName());
        accountDTO.setEmail(account.getEmail());
        return accountDTO;
    }
}
