package com.example.homework.infrastructure.repository;

import com.example.homework.infrastructure.entity.Order;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderRepository extends JpaRepository<Order, Long> {
}
