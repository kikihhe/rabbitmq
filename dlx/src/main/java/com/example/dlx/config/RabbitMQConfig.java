package com.example.dlx.config;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.TopicExchange;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.HashMap;

/**
 * @author : 小何
 * @Description :
 * @date : 2023-01-05 21:29
 */
@Configuration
public class RabbitMQConfig {
    public static final String MESSAGE_EXCHANGE_NAME = "message_exchange";
    public static final String MESSAGE_QUEUE_NAME = "message_queue";



    @Bean
    public TopicExchange exchange() {
        return new TopicExchange(MESSAGE_EXCHANGE_NAME, true, false);
    }
    @Bean
    public Queue queue() {
        HashMap<String, Object> map = new HashMap<>();
        // 指定队列消息的TTL, 指定5s死亡，让消息进入死信队列
        map.put("x-message-ttl", 5000);
        // 指定message_queue队列中的消息万一死了，由路由键为dead_queue的队列收容死消息
        map.put("x-dead-letter-exchange", "dxl_exchange");
        map.put("x-dead-letter-routing-key", "dead_queue_routingkey");

        return new Queue(MESSAGE_QUEUE_NAME, true, false, false, map);
    }
    @Bean
    public Binding binding() {
        return BindingBuilder.bind(queue()).to(exchange()).with("user.insert");
    }

}
