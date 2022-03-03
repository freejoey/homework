package com.example.homework.domain.service;

import com.example.homework.client.dto.MqMessage;
import com.example.homework.infrastructure.entity.Income;
import com.example.homework.infrastructure.repository.AccountRepository;
import com.example.homework.infrastructure.repository.IncomeRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.security.InvalidParameterException;
import java.time.Instant;
import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class IncomeService {
    private final IncomeRepository incomeRepository;
    private final AccountRepository accountRepository;

    public List<Income> getAll() {
        return incomeRepository.findAll();
    }

    public Income get(Long iid) {
        return incomeRepository.findById(iid)
                .orElseThrow(() -> new InvalidParameterException(String.format("invalid iid:%s", iid)));
    }

    @Transactional
    public void income(MqMessage mqMessage) {
        if (accountRepository.updatePropertiesByUid(mqMessage.getUid(), mqMessage.getBenefit()) <= 0) {
            log.error("update income failed, msg:{}", mqMessage);
            return;
        }
        incomeRepository.save(Income.builder()
                .uid(mqMessage.getUid())
                .orderId(mqMessage.getOrderId())
                .benefit(mqMessage.getBenefit())
                .createdAt(Instant.now())
                .updatedAt(Instant.now())
                .build());
    }
}
