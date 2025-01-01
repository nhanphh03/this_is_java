package com.connection.database.entity.hibernate;

import jakarta.persistence.*;
import lombok.Data;

@Data
public class Customer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
}
