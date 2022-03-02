package com.example.homework.api.request;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class WithdrawRequest {
    private Long uid;
    private BigDecimal amount;
}
