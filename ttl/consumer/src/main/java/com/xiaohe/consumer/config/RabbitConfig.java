package com.xiaohe.consumer.config;

import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Slf4j
@Configuration
public class RabbitConfig {

    @Bean
    public RabbitTemplate rabbitTemplate(ConnectionFactory connectionFactory) {
        RabbitTemplate rabbitTemplate = new RabbitTemplate();
        rabbitTemplate.setConnectionFactory(connectionFactory);
        /*设置开启Mandatory才能触发回调函数，无论消息推送结果怎么样都强制调用回调函数*/
        rabbitTemplate.setMandatory(true);

        /*消息发送到Exchange的回调，无论成功与否*/
        /**
         * 消息从生产者发送到交换机的回调函数, 无论消息发送成功与否都执行
         */
        rabbitTemplate.setConfirmCallback((correlationData, ack, cause) -> {
            log.info("ConfirmCallback：" + "相关数据：" + correlationData);
            log.info("ConfirmCallback：" + "确认情况：" + ack);
            log.info("ConfirmCallback：" + "原因：" + cause);
        });

        /*消息从Exchange路由到Queue失败的回调*/
        /**
         * 消息从交换机发送到队列的回调函数，无论消息发送成功与否都执行
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
