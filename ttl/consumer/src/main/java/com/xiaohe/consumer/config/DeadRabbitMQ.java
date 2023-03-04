package com.xiaohe.consumer.config;


import com.xiaohe.basic.constant.RabbitMQ;
import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.TopicExchange;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * 死信队列的配置类
 */
@Configuration
public class DeadRabbitMQ {
    // 注入死信交换机
    @Bean(RabbitMQ.EXCHANGE.DEAD_EXCHANGE)
    public TopicExchange deadExchange() {
        return new TopicExchange(RabbitMQ.EXCHANGE.DEAD_EXCHANGE, true, false, null);
    }

    // 注入死信队列
    @Bean(RabbitMQ.QUEUE.DEAD_QUEUE)
    public Queue deadQueue() {
        return new Queue(RabbitMQ.QUEUE.DEAD_QUEUE, true, false, false, null);
    }
    // 将死信队列与交换机绑定
    @Bean
    public Binding binding() {
        return BindingBuilder.bind(deadQueue()).to(deadExchange()).with(RabbitMQ.ROUTING_KEY.DEAD_QUEUE_ROUTING_KEY);
    }

}
