package com.example.homework.domain.service;

import com.example.homework.infrastructure.entity.Withdraw;
import com.example.homework.infrastructure.repository.AccountRepository;
import com.example.homework.infrastructure.repository.WithdrawRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.time.Instant;

@Service
@RequiredArgsConstructor
@Slf4j
public class WithdrawService {
    private final WithdrawRepository withdrawRepository;
    private final AccountRepository accountRepository;

    @Transactional
    public Withdraw withdraw(long uid, BigDecimal amount) {
        if (accountRepository.updatePropertiesByUid(uid, amount.negate()) <= 0) {
            throw new IllegalArgumentException(String.format("illegal params, %s,%s", uid, amount));
        }
        return withdrawRepository.saveAndFlush(Withdraw.builder()
                .uid(uid)
                .amount(amount)
                .createdAt(Instant.now())
                .updatedAt(Instant.now())
                .build());
    }
}
