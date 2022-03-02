package com.example.homework.infrastructure.entity;

import com.example.homework.infrastructure.enums.AccountStatus;
import lombok.Data;
import org.hibernate.annotations.Proxy;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import java.math.BigDecimal;
import java.time.Instant;

@Entity
@Table(name = "account")
@Data
@Proxy(lazy = false)
public class Account {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long uid;

    private BigDecimal properties;

    @Enumerated(EnumType.STRING)
    private AccountStatus status;

    private Instant createdAt;

    private Instant updatedAt;
}
