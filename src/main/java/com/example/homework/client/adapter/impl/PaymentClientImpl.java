package com.example.homework.client.adapter.impl;

import com.example.homework.client.adapter.PaymentClient;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

@Service
@Slf4j
public class PaymentClientImpl implements PaymentClient {

    @Override
    public boolean charge(long uid, BigDecimal amount) {
        log.info("charge to payment platform successfully, uid:{}, amount:{}", uid, amount);
        return true;
    }
}
