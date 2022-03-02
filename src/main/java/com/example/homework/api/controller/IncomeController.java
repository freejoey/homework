package com.example.homework.api.controller;

import com.example.homework.api.mapper.IncomeResponseMapper;
import com.example.homework.api.response.IncomeResponse;
import com.example.homework.domain.service.IncomeService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/income")
@RequiredArgsConstructor
public class IncomeController {
    private final IncomeService incomeService;
    private final IncomeResponseMapper incomeResponseMapper;

    @GetMapping("/all")
    public List<IncomeResponse> all() {
        return incomeService.getAll().stream()
                .map(incomeResponseMapper::toResource)
                .collect(Collectors.toList());
    }

    @GetMapping("/{iid}")
    public IncomeResponse get(@PathVariable("iid") Long iid) {
        return incomeResponseMapper.toResource(incomeService.get(iid));
    }
}
