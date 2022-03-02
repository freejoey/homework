package com.example.homework.api.response;

import com.example.homework.infrastructure.enums.AccountStatus;
import lombok.Data;

import java.math.BigDecimal;
import java.time.Instant;

@Data
public class AccountResponse {
    private Long id;

    private Long uid;

    private BigDecimal properties;

    private AccountStatus status;

    private Instant createdAt;

    private Instant updatedAt;
}
