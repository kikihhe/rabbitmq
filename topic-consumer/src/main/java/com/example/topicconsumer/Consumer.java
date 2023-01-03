package com.example.topicconsumer;


import com.rabbitmq.client.Channel;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

import java.io.IOException;


@Component
public class Consumer {
    @RabbitListener(queues = "springboot_topic_queue")
    public void process(Message message, Channel channel) throws IOException {
        System.out.println("消息对象Message为: " + message);
        System.out.println("消费者路由键为 blog.insert, 消息为: " + new String(message.getBody()));
        // 手动ack
        channel.basicAck(message.getMessageProperties().getDeliveryTag(),false);
    }
}
