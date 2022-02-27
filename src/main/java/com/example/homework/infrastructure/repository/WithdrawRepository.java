package com.example.homework.infrastructure.repository;

import com.example.homework.infrastructure.entity.Withdraw;
import org.springframework.data.jpa.repository.JpaRepository;

public interface WithdrawRepository extends JpaRepository<Withdraw, Long> {
}
