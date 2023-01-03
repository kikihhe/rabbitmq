package com.example.topicproducer.config;

import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.TopicExchange;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 *
 *
 * Topic模式只声明交换机即可，消息发送到交换机，再由交换机推送给队列
 */
@Configuration
public class RabbitMQConfig {
    private static final String EXCHANGE_NAME = "springboot_topic_exchange";



    @Bean
    public TopicExchange exchange() {
        return new TopicExchange(EXCHANGE_NAME, true, false);
    }

}
