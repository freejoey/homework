package com.example.homework.api.mapper;

import com.example.homework.api.response.AccountResponse;
import com.example.homework.infrastructure.entity.Account;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface AccountResponseMapper {
    AccountResponse toResource(Account account);
}
