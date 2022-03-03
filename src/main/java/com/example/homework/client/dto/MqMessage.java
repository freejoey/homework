package com.example.homework.client.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class MqMessage {
    private Long uid;

    private Long orderId;

    private BigDecimal benefit;
}
