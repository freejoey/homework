package com.example.homework.api.controller;

import com.example.homework.api.mapper.WithdrawResponseMapper;
import com.example.homework.api.request.WithdrawRequest;
import com.example.homework.api.response.WithdrawResponse;
import com.example.homework.domain.service.WithdrawService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/account")
@RequiredArgsConstructor
public class WithdrawController {
    private final WithdrawService withdrawService;
    private final WithdrawResponseMapper withdrawResponseMapper;

    @PostMapping("/withdraw")
    public WithdrawResponse withdraw(@RequestBody WithdrawRequest withdrawRequest) {
        return withdrawResponseMapper.toResource(
                withdrawService.withdraw(withdrawRequest.getUid(), withdrawRequest.getAmount()));
    }
}
