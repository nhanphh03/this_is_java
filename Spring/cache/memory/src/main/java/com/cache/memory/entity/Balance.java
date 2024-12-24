package com.cache.memory.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.math.BigInteger;

@Getter
@Setter
@Entity
@Table(name = "balance")
public class Balance {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long balanceId;

    private BigInteger availableBalance;

    private BigInteger holdBalance;


    @JoinColumn(name = "accountId", nullable = false)
    private Long accountId;

    private Long customerId;
}
