package com.example.homework.client.consumer;

import com.example.homework.client.adapter.IncomeMqConsumer;
import com.example.homework.client.dto.MqMessage;
import com.example.homework.domain.service.IncomeService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Rule;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.test.util.TestPropertyValues;
import org.springframework.context.ApplicationContextInitializer;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;
import org.testcontainers.containers.GenericContainer;
import org.testcontainers.utility.DockerImageName;

import java.math.BigDecimal;

import static com.example.homework.client.adapter.IncomeMqConsumer.MQ_KEY;
import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@ContextConfiguration(initializers = IncomeMqConsumerTest.Initializer.class)
class IncomeMqConsumerTest {
    @MockBean
    private IncomeService incomeService;
    @Autowired
    private RedisTemplate<String, Object> redisTemplate;
    @Autowired
    private IncomeMqConsumer incomeMqConsumer;

    private final ObjectMapper objectMapper = new ObjectMapper();

    @Rule
    public static GenericContainer redis = new GenericContainer<>(DockerImageName.parse("redis:5.0.3-alpine"))
            .withExposedPorts(6379);

    static {
        redis.start();
    }

    public static class Initializer implements ApplicationContextInitializer<ConfigurableApplicationContext> {

        @Override
        public void initialize(ConfigurableApplicationContext configurableApplicationContext) {
            TestPropertyValues values = TestPropertyValues.of(
                    "spring.redis.host=" + redis.getHost(),
                    "spring.redis.port=" + redis.getMappedPort(6379)
            );
            values.applyTo(configurableApplicationContext);
        }
    }

    @BeforeAll
    public static void setup() {
    }

    @AfterAll
    public static void tearDown() {
        redis.stop();
    }

    @Test
    void should_receive_message_when_sent() throws JsonProcessingException {
        incomeMqConsumer.destroy();
        MqMessage mqMessage = MqMessage.builder()
                .benefit(BigDecimal.ONE)
                .uid(1L)
                .orderId(99L)
                .build();
        redisTemplate.opsForList().leftPush(MQ_KEY, objectMapper.writeValueAsString(mqMessage));
        assertThat(incomeMqConsumer.blockReadMessage()).isNotEmpty().get().isEqualTo(mqMessage);
    }

    @Test
    void should_return_empty_when_message_illegal() {
        incomeMqConsumer.destroy();
        redisTemplate.opsForList().leftPush(MQ_KEY, "illegal message");
        assertThat(incomeMqConsumer.blockReadMessage()).isEmpty();
    }
}