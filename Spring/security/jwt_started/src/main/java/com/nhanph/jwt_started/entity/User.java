package com.nhanph.jwt_started.entity;

import jakarta.persistence.*;
import jdk.jfr.DataAmount;

@Entity
@Table(name = "user")
@DataAmount
public class User {
    @Id
    @GeneratedValue
    private Long id;

    @Column(nullable = false, unique = true)
    private String username;
    private String password;
}
