package com.example.homework.api.mapper;

import com.example.homework.api.response.WithdrawResponse;
import com.example.homework.infrastructure.entity.Withdraw;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface WithdrawResponseMapper {
    WithdrawResponse toResource(Withdraw withdraw);
}
