package com.example.homework.api.controller;

import com.example.homework.api.response.AccountResponse;
import com.example.homework.api.mapper.AccountResponseMapper;
import com.example.homework.domain.service.AccountService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/account")
@RequiredArgsConstructor
public class AccountController {
    private final AccountService accountService;
    private final AccountResponseMapper accountResponseMapper;

    @GetMapping("/{aid}")
    public AccountResponse accountInfo(@PathVariable("aid") Long aid) {
        return accountResponseMapper.toResource(accountService.get(aid));
    }
}
