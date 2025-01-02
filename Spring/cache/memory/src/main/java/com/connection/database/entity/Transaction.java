package com.connection.database.entity;

import jakarta.persistence.*;
import lombok.*;
import java.math.BigInteger;
import java.sql.Date;
import java.sql.Timestamp;

@Entity
@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Transaction {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "transaction_id")
    private Long transactionID;

    @Column(name = "account_id")
    private Long accountID;

    @Column(name = "transaction_date")
    private Timestamp transactionDate;

    @Column(name = "amount")
    private BigInteger amount;

    @Column(name = "transaction_type")
    private String transactionType;

    @Column(name = "status")
    private String status;

    @Column(name = "currency")
    private String currency;

    @Column(name = "location")
    private String location;

}
