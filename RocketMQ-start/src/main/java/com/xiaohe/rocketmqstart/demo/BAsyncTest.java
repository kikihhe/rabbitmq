package com.xiaohe.rocketmqstart.demo;

import com.xiaohe.rocketmqstart.constants.MQConstant;
import org.apache.rocketmq.client.consumer.DefaultMQPushConsumer;
import org.apache.rocketmq.client.producer.DefaultMQProducer;
import org.apache.rocketmq.client.producer.SendCallback;
import org.apache.rocketmq.client.producer.SendResult;
import org.apache.rocketmq.common.message.Message;
import org.junit.Test;

import java.nio.charset.StandardCharsets;
import java.time.LocalDateTime;

/**
 * @ClassName BAsyncTest
 * @Description 异步消息
 * @Author 何
 * @Date 2023-07-09 15:56
 * @Version 1.0
 */
public class BAsyncTest {
    @Test
    public void produce() throws Exception {
        DefaultMQProducer producer = new DefaultMQProducer("async-producer-group");
        producer.setNamesrvAddr(MQConstant.NAME_SRV_ADDR);

        producer.start();
        Message message = new Message();
        message.setTopic("testTopic");
        message.setBody("发送了一条消息".getBytes(StandardCharsets.UTF_8));
        producer.send(message, new SendCallback() {
            @Override
            public void onSuccess(SendResult sendResult) {
                System.out.println(LocalDateTime.now() + " --- 消息被RocketMQ成功接收，可以发送下一条消息");
            }
            @Override
            public void onException(Throwable e) {
                System.out.println(e);
                System.out.println(LocalDateTime.now() + " --- 消息出现异常");
            }
        });

        System.out.println("我先执行");
        Thread.sleep(10000);
        producer.shutdown();

    }


    @Test
    public void consume() {

    }
}
