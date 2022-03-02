package com.example.homework.api.response;

import lombok.Data;

import java.math.BigDecimal;
import java.time.Instant;

@Data
public class IncomeResponse {
    private Long id;

    private Long uid;

    private Long orderId;

    private BigDecimal benefit;

    private Instant createdAt;

    private Instant updatedAt;
}
