package com.example.topicconsumer.config;

import org.springframework.amqp.core.*;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author : 小何
 * @Description :
 * @date : 2023-01-03 21:21
 */
@Configuration
public class RabbitMQConfig {
    private static final String EXCHANGE_NAME = "springboot_topic_exchange";
    private static final String QUEUE_NAME = "springboot_topic_queue";

    @Bean
    public Queue queue() {
        return new Queue(QUEUE_NAME, true, false, false);
    }
    @Bean
    public TopicExchange exchange() {
        return new TopicExchange(EXCHANGE_NAME, true, false);
    }
    @Bean
    public Binding queueBinding(Queue queue, TopicExchange topicExchange) {
        return BindingBuilder.bind(queue).to(topicExchange).with("blog.insert");
    }


}
