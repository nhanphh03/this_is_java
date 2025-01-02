package com.connection.database.dto;

import jakarta.validation.constraints.Pattern;
import lombok.Data;

import java.math.BigInteger;

@Data
public class TransactionRequestToVNPay {

    private Long transactionId;
    private Long accountId;
    private BigInteger amount;
    @Pattern(regexp = "^(deposit|withdrawal)$", message = "!")
    private String transactionType;
    private String currency;
    @Pattern(regexp = "^(vn|en)$", message = "Language must be either 'vn' or 'en'")
    private String location;
}
