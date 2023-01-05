package com.example.ttlconsumer.config;

import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

/**
 * @author : 小何
 * @Description :
 * @date : 2023-01-05 20:32
 */
@Component
public class Consumer {

    @RabbitListener(queues = "SPRINGBOOT-TTL-QUEUE")
    public void process(Message message) {
        System.out.println("接收到消息: " + new String(message.getBody()));
        System.out.println("它的过期时间: " + message.getMessageProperties().getExpiration());
    }
}
