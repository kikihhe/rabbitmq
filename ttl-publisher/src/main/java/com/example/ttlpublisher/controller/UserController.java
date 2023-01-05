package com.example.ttlpublisher.controller;

import com.example.ttlpublisher.config.RabbitMQConfig;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.nio.charset.StandardCharsets;

/**
 * @author : 小何
 * @Description :
 * @date : 2023-01-05 19:54
 */
@RestController
public class UserController {

    @Autowired
    private RabbitTemplate rabbitTemplate;

    @GetMapping("/hello")
    public String hello() {
        Message message = new Message("你好，这里发送消息".getBytes(StandardCharsets.UTF_8));
        message.getMessageProperties().setExpiration("10000");

        rabbitTemplate.convertAndSend(RabbitMQConfig.EXCHANGE_NAME, "user.insert", message);
        return "message对象为: " + message.toString();
    }
}
