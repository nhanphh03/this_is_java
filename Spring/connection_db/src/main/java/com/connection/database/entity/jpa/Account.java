package com.connection.database.entity.jpa;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "account")
@AllArgsConstructor
@Getter
@Setter
@NoArgsConstructor
public class Account {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "account_id")
    private Long id;

    @Column(name = "name")
    private String name;

    @Column(name = "email")
    private String email;

    @Column(name = "age")
    private int age;
}
