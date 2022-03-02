package com.example.homework.infrastructure.repository;

import com.example.homework.HomeworkApplicationTests;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import static org.assertj.core.api.Assertions.assertThat;

class IncomeRepositoryTest extends HomeworkApplicationTests {

    @Autowired
    private IncomeRepository incomeRepository;

    @Test
    void should_return_incomes_when_uid_exist() {
        assertThat(incomeRepository.findAll()).isNotEmpty();
    }
}