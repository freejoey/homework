package com.example.homework.infrastructure.repository;

import com.example.homework.infrastructure.entity.Income;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IncomeRepository extends JpaRepository<Income, Long> {

}
