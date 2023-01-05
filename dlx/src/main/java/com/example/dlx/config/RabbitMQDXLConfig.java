package com.example.dlx.config;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.TopicExchange;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author : 小何
 * @Description :
 * @date : 2023-01-05 21:25
 */
@Configuration
public class RabbitMQDXLConfig {
    public static final String DXL_EXCHANGE_NAME = "dxl_exchange";
    public static final String DXL_QUEUE_NAME = "dxl_queue";



    @Bean
    public TopicExchange DXLExchange() {
        return new TopicExchange(DXL_EXCHANGE_NAME, true, false);
    }
    @Bean
    public Queue DXLQueue() {
        return new Queue(DXL_QUEUE_NAME, true, false, false);
    }
    @Bean
    public Binding DXLBinding() {
        // 死信队列的路由键为 dead_queue_routingkey
        return BindingBuilder.bind(DXLQueue()).to(DXLExchange()).with("dead_queue_routingkey");
    }

}
