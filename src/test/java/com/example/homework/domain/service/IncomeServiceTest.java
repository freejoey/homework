package com.example.homework.domain.service;

import com.example.homework.client.dto.MqMessage;
import com.example.homework.infrastructure.entity.Income;
import com.example.homework.infrastructure.repository.AccountRepository;
import com.example.homework.infrastructure.repository.IncomeRepository;
import org.assertj.core.util.Lists;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.doAnswer;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

class IncomeServiceTest {
    private final IncomeRepository incomeRepository = mock(IncomeRepository.class);
    private final AccountRepository accountRepository = mock(AccountRepository.class);
    private final IncomeService incomeService = new IncomeService(incomeRepository, accountRepository);

    private final Income income = Income.builder()
            .id(100L)
            .uid(200L)
            .benefit(BigDecimal.valueOf(32.4))
            .build();

    @BeforeEach
    void setup() {
        when(incomeRepository.findAll()).thenReturn(Lists.list(income));
        when(incomeRepository.findById(100L)).thenReturn(Optional.of(income));
    }

    @Test
    void should_return_incomes_when_get_all() {
        assertThat(incomeService.getAll()).isEqualTo(Lists.list(income));
    }

    @Test
    void should_return_income_when_get_exist_id() {
        assertThat(incomeService.get(100L)).isEqualTo(income);
    }

    @Test
    void should_throw_when_get_not_exist_id() {
        assertThrows(IllegalArgumentException.class, () -> incomeService.get(101L));
    }

    @Test
    void should_update_account_when_receive_income() {
        MqMessage mqMessage = MqMessage.builder().uid(1L).benefit(BigDecimal.ONE).build();
        incomeService.income(mqMessage);
        verify(accountRepository).updatePropertiesByUid(mqMessage.getUid(), mqMessage.getBenefit());
    }

    @Test
    void t() {
        MqMessage mqMessage = MqMessage.builder().uid(1L).benefit(BigDecimal.ONE).build();
        doAnswer(invocation -> {
            Long uid = invocation.getArgument(0);
            BigDecimal benefit = invocation.getArgument(1);
            if (!uid.equals(mqMessage.getUid()) || !benefit.equals(mqMessage.getBenefit())) {
                throw new AssertionError("Unexpected invocation: " + invocation);
            }
            return 1;
        }).when(accountRepository).updatePropertiesByUid(mqMessage.getUid(), mqMessage.getBenefit());
        incomeService.income(mqMessage);
    }
}