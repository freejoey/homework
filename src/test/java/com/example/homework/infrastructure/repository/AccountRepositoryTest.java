package com.example.homework.infrastructure.repository;

import com.example.homework.HomeworkApplicationTests;
import com.example.homework.infrastructure.entity.Account;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.Rollback;

import java.math.BigDecimal;

import static org.assertj.core.api.Assertions.assertThat;


class AccountRepositoryTest extends HomeworkApplicationTests {
    @Autowired
    private AccountRepository accountRepository;

    @Test
    void should_return_account_info_when_id_exist() {
        Account a = accountRepository.getById(1L);
        assertThat(a).isNotNull();
    }

    @Test
    @Rollback
    void should_update_properties_when_do_update_properties() {
        Account origin = accountRepository.getById(1L);
        BigDecimal before = origin.getProperties();
        BigDecimal delta = BigDecimal.valueOf(-3.4);
        int affected = accountRepository.updatePropertiesByUid(origin.getUid(), delta);
        assertThat(affected).isEqualTo(1);
        Account updated = accountRepository.getById(1L);
        assertThat(before.add(delta)).isEqualTo(updated.getProperties());
    }

    @Test
    @Rollback
    void should_not_update_properties_when_uid_not_exist() {
        assertThat(accountRepository.updatePropertiesByUid(123L, BigDecimal.ONE)).isZero();
    }
}