package com.example.homework.client.consumer;

import com.example.homework.client.dto.MqMessage;
import com.example.homework.domain.service.IncomeService;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import java.util.Objects;
import java.util.Optional;
import java.util.concurrent.TimeUnit;

@Component
@Slf4j
@RequiredArgsConstructor
public class IncomeMqConsumer implements Runnable {
    private final RedisTemplate<String, Object> redisTemplate;
    private final IncomeService incomeService;

    private static final String MQ_KEY = "INCOME_MQ";
    private static final long READ_MESSAGE_INTERVAL = 60;

    private boolean running = true;
    private ObjectMapper objectMapper = new ObjectMapper();

    @PostConstruct
    public void init() {
        new Thread(this).start();
    }

    @PreDestroy
    public void destroy() {
        this.running = false;
    }

    @Override
    public void run() {
        while (running) {
            blockReadMessage().ifPresent(incomeService::income);
        }
    }

    private Optional<MqMessage> blockReadMessage() {
        Object data;
        try {
            data = redisTemplate.opsForList().rightPop(MQ_KEY, READ_MESSAGE_INTERVAL, TimeUnit.SECONDS);
            log.info("scanner waiting queue size:{}", redisTemplate.opsForList().size(MQ_KEY));
        } catch (Exception ignore) {
            return Optional.empty();
        }
        if (Objects.isNull(data)) {
            log.warn("scanner consumer read null");
            return Optional.empty();
        }
        log.info("scanner received msg:{}", data);
        try {
            return Optional.of(objectMapper.readValue(data.toString(), MqMessage.class));
        } catch (Exception e) {
            log.error("scanner service convert message failed:{}", data);
            return Optional.empty();
        }
    }
}
