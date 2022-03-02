package com.example.homework.api.mapper;

import com.example.homework.api.response.IncomeResponse;
import com.example.homework.infrastructure.entity.Income;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface IncomeResponseMapper {
    IncomeResponse toResource(Income income);
}
