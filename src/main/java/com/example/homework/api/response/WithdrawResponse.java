package com.example.homework.api.response;

import lombok.Data;

import java.math.BigDecimal;
import java.time.Instant;

@Data
public class WithdrawResponse {
    private Long id;

    private Long uid;

    private BigDecimal amount;

    private Instant createdAt;

    private Instant updatedAt;
}
