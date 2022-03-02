package com.example.homework.domain.service;

import com.example.homework.infrastructure.entity.Income;
import com.example.homework.infrastructure.repository.IncomeRepository;
import org.assertj.core.util.Lists;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class IncomeServiceTest {
    private final IncomeRepository incomeRepository = mock(IncomeRepository.class);
    private final IncomeService incomeService = new IncomeService(incomeRepository);

    private final Income income = Income.builder()
            .id(100L)
            .uid(200L)
            .benefit(BigDecimal.valueOf(32.4))
            .build();

    @BeforeEach
    void init() {
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
}