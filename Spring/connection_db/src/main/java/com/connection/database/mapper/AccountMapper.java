package com.connection.database.mapper;

import com.connection.database.dto.AccountDTO;
import com.connection.database.entity.Account;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface AccountMapper {

    //https://www.baeldung.com/mapstruct
    AccountDTO toDto(Account account);
}
