package com.xiaohe.publish.config;

import com.rabbitmq.client.Channel;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.connection.Connection;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Slf4j
@Configuration
public class RabbitConfig {
    @Autowired
    private ConnectionFactory connectionFactory;

    public void a() {
        Channel channel = connectionFactory.createConnection().createChannel(true);
//        channel.
    }


    @Bean
    public RabbitTemplate rabbitTemplate(ConnectionFactory connectionFactory) {
        RabbitTemplate rabbitTemplate = new RabbitTemplate();
        rabbitTemplate.setConnectionFactory(connectionFactory);
        rabbitTemplate.convertAndSend();
        /*设置开启Mandatory才能触发回调函数，无论消息推送结果怎么样都强制调用回调函数*/
        rabbitTemplate.setMandatory(true);

        /*消息发送到Exchange的回调，无论成功与否*/
        /**
         * 消息确认机制第一步: confirm
         * 消息从生产者发送到交换机的回调函数, 无论消息发送成功与否都执行
         * correlationData: 保存消息id以及相关信息
         * ack: 交换机是否收到消息，收到为true
         * cause: 消息接收不到原因，成功接收消息则为null
         */
        rabbitTemplate.setConfirmCallback((correlationData, ack, cause) -> {

            // 打印日志
            log.info("ConfirmCallback：" + "相关数据：" + correlationData);
            log.info("ConfirmCallback：" + "确认情况：" + ack);
            log.info("ConfirmCallback：" + "原因：" + cause);
            // 存入数据库中定时重发.
            // ...
        });

        /*消息从Exchange路由到Queue失败的回调*/
        /**
         * 消息确认机制第二步: return
         * 消息从交换机发送到队列的回调函数，无论消息发送成功与否都执行
         * message: 被退回的消息体
         * replyCode: 错误编码
         * replyText: 消息接收失败的原因
         * exchange: 此条消息的目标交换机
         * routingKey: 此条消息的路由键
         */
        rabbitTemplate.setReturnCallback((message, replyCode, replyText, exchange, routingKey) -> {
            log.info("ReturnCallback：" + "消息：" + message);
            log.info("ReturnCallback：" + "回应码：" + replyCode);
            log.info("ReturnCallback：" + "回应信息：" + replyText);
            log.info("ReturnCallback：" + "交换机：" + exchange);
            log.info("ReturnCallback：" + "路由键：" + routingKey);
        });
        return rabbitTemplate;
    }
}
