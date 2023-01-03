package com.example.topicproducer;


import org.junit.jupiter.api.Test;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class TestTopicProducerApplication {
    private static final String EXCHANGE_NAME = "springboot_topic_exchange";

    @Autowired
    private RabbitTemplate rabbitTemplate;

    @Test
    public void sendMessage() {
        String message = "你好，topic模式";
        rabbitTemplate.convertAndSend(EXCHANGE_NAME, "blog.insert", message);

    }

}
