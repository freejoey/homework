package com.example.homework.infrastructure.repository;

import com.example.homework.infrastructure.entity.Account;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AccountRepository extends JpaRepository<Account, Long> {
}
