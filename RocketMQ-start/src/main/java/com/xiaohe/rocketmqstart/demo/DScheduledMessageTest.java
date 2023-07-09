package com.xiaohe.rocketmqstart.demo;

import com.xiaohe.rocketmqstart.constants.MQConstant;
import org.apache.rocketmq.client.exception.MQBrokerException;
import org.apache.rocketmq.client.exception.MQClientException;
import org.apache.rocketmq.client.producer.DefaultMQProducer;
import org.apache.rocketmq.client.producer.SendResult;
import org.apache.rocketmq.common.message.Message;
import org.apache.rocketmq.remoting.exception.RemotingException;
import org.junit.Test;

import java.nio.charset.StandardCharsets;
import java.time.LocalDateTime;

/**
 * @ClassName DScheduledMessageTest
 * @Description 延时消息
 * @Author 何
 * @Date 2023-07-09 18:17
 * @Version 1.0
 */
public class DScheduledMessageTest {

    @Test
    public void produce() throws MQClientException, MQBrokerException, RemotingException, InterruptedException {
        DefaultMQProducer producer = new DefaultMQProducer("scheduled-producer-group");
        producer.setNamesrvAddr(MQConstant.NAME_SRV_ADDR);
        producer.start();
        Message message = new Message();
        message.setBody("延时消息测试".getBytes(StandardCharsets.UTF_8));
        message.setTopic("testTopic");
        message.setDelayTimeLevel(6);
        System.out.println(LocalDateTime.now());
        SendResult sendResult = producer.send(message);
        // 发送状态: SEND_OK, 时间: 2023-07-09T18:23:44.733
        System.out.println("发送状态: " + sendResult.getSendStatus() +", 时间: " + LocalDateTime.now());

    }

    public void consume() {


    }
}
