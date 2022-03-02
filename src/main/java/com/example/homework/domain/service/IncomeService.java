package com.example.homework.domain.service;

import com.example.homework.infrastructure.entity.Income;
import com.example.homework.infrastructure.repository.IncomeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.security.InvalidParameterException;
import java.util.List;

@Service
@RequiredArgsConstructor
public class IncomeService {
    private final IncomeRepository incomeRepository;

    public List<Income> getAll() {
        return incomeRepository.findAll();
    }

    public Income get(Long iid) {
        return incomeRepository.findById(iid)
                .orElseThrow(() -> new InvalidParameterException(String.format("invalid iid:%s", iid)));
    }
}
