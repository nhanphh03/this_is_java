package com.connection.database.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class AccountDTO {

    @NotNull(message = "Account cannot be null")
    @Min(value = 1, message = "Amount must be at least 1")
    private Long accountId;

    @NotNull(message = "Email cannot be null")
    @Email
    private String email;

    @NotNull(message = "Name cannot be null")
    private String customerName;



}
