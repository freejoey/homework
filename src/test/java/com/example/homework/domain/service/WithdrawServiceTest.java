package com.example.homework.domain.service;

import com.example.homework.infrastructure.entity.Withdraw;
import com.example.homework.infrastructure.repository.AccountRepository;
import com.example.homework.infrastructure.repository.WithdrawRepository;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

class WithdrawServiceTest {
    private final WithdrawRepository withdrawRepository = mock(WithdrawRepository.class);
    private final AccountRepository accountRepository = mock(AccountRepository.class);
    private final WithdrawService withdrawService = new WithdrawService(withdrawRepository, accountRepository);

    @Test
    void should_save_withdraw_and_update_account_when_do_withdraw() {
        Withdraw withdraw = Withdraw.builder().uid(1L).amount(BigDecimal.ONE).build();
        when(accountRepository.updatePropertiesByUid(1L, BigDecimal.ONE)).thenReturn(1);
        withdrawService.withdraw(withdraw.getUid(), withdraw.getAmount());
        verify(accountRepository).updatePropertiesByUid(withdraw.getUid(), withdraw.getAmount());
        verify(withdrawRepository).saveAndFlush(any());
    }

    @Test
    void should_not_save_withdraw_and_update_account_when_uid_not_exist() {
        long notExistUid = 121212L;
        when(accountRepository.updatePropertiesByUid(notExistUid, BigDecimal.ONE)).thenReturn(0);
        assertThrows(IllegalArgumentException.class, () -> withdrawService.withdraw(notExistUid, BigDecimal.ONE));
        verify(accountRepository).updatePropertiesByUid(notExistUid, BigDecimal.ONE);
    }
}