package com.example.homework.client.adapter;

import java.math.BigDecimal;

public interface PaymentClient {
    boolean charge(long uid, BigDecimal amount);
}
