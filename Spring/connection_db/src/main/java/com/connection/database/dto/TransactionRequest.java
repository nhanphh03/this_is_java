package com.connection.database.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import org.springframework.data.domain.Pageable;

@Data
public class TransactionRequest {
    private Pageable pageable;

    @JsonProperty("account_id")
    private Long accountID;
}
