package com.example.homework.domain.service;

import com.example.homework.infrastructure.entity.Account;
import com.example.homework.infrastructure.repository.AccountRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AccountService {
    private final AccountRepository accountRepository;

    public Account get(long aid) {
        return accountRepository.getById(aid);
    }
}
