package com.cache.memory.mapper;

import com.cache.memory.dto.AccountDTO;
import com.cache.memory.entity.Account;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface AccountMapper {

    //https://www.baeldung.com/mapstruct
    AccountDTO toDto(Account account);
}
