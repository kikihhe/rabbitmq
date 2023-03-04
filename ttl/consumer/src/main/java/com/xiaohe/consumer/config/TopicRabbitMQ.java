package com.xiaohe.consumer.config;

import com.xiaohe.basic.constant.RabbitMQ;
import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.TopicExchange;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.HashMap;

/**
 * 主题交换机的配置类
 */
@Configuration
public class TopicRabbitMQ {

    // 注入普通交换机(topic模式)
    @Bean(RabbitMQ.EXCHANGE.NORMAL_EXCHANGE)
    public TopicExchange normalExchange() {
        return new TopicExchange(RabbitMQ.EXCHANGE.NORMAL_EXCHANGE, true, false, null);
    }

    // 注入普通队列
    @Bean(RabbitMQ.QUEUE.NORMAL_QUEUE)
    public Queue normalQueue() {
        HashMap<String, Object> map = new HashMap<>();
        // 指定死信交换机与死信队列的路由键(即死亡消息到达交换机后走哪条路由键到达死信队列)
        map.put("x-dead-letter-exchange", RabbitMQ.EXCHANGE.DEAD_EXCHANGE);
        map.put("x-dead-letter-routing-key", RabbitMQ.ROUTING_KEY.DEAD_QUEUE_ROUTING_KEY);
        return new Queue(RabbitMQ.QUEUE.NORMAL_QUEUE, true, false, false, map);
    }

    // 将普通交换机与普通队列绑定, 路由键为 normal_queue_routing_key
    @Bean
    public Binding binding() {
        return BindingBuilder.bind(normalQueue()).to(normalExchange()).with(RabbitMQ.ROUTING_KEY.NORMAL_QUEUE_ROUTING_KEY);
    }
}
