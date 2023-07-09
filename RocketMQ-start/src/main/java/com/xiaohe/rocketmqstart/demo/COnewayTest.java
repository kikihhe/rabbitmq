package com.xiaohe.rocketmqstart.demo;

import com.xiaohe.rocketmqstart.constants.MQConstant;
import org.apache.rocketmq.client.producer.DefaultMQProducer;
import org.apache.rocketmq.common.message.Message;
import org.junit.Test;

import java.nio.charset.StandardCharsets;

/**
 * @ClassName COnewayTest
 * @Description 单向发送消息的方式
 * @Author 何
 * @Date 2023-07-09 16:27
 * @Version 1.0
 */
public class COnewayTest {
    @Test
    public void produce() throws Exception {
        DefaultMQProducer producer = new DefaultMQProducer("oneway-producer-group");
        producer.setNamesrvAddr(MQConstant.NAME_SRV_ADDR);
        producer.start();
        Message message = new Message("testTopic", "日志1".getBytes(StandardCharsets.UTF_8));
        producer.sendOneway(message);
        Thread.sleep(10000);
        producer.shutdown();

    }
}
