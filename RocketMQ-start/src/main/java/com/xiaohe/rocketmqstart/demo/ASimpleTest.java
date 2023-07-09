package com.xiaohe.rocketmqstart.demo;

import com.xiaohe.rocketmqstart.constants.MQConstant;
import org.apache.rocketmq.client.consumer.DefaultMQPushConsumer;
import org.apache.rocketmq.client.consumer.listener.ConsumeConcurrentlyContext;
import org.apache.rocketmq.client.consumer.listener.ConsumeConcurrentlyStatus;
import org.apache.rocketmq.client.consumer.listener.MessageListenerConcurrently;
import org.apache.rocketmq.client.exception.MQBrokerException;
import org.apache.rocketmq.client.exception.MQClientException;
import org.apache.rocketmq.client.producer.DefaultMQProducer;
import org.apache.rocketmq.client.producer.SendResult;
import org.apache.rocketmq.common.message.Message;
import org.apache.rocketmq.common.message.MessageExt;
import org.apache.rocketmq.remoting.exception.RemotingException;
import org.junit.Test;

import java.nio.charset.StandardCharsets;
import java.util.List;

/**
 * @ClassName ASimpleTest
 * @Description 测试类
 * @Author 何
 * @Date 2023-07-09 14:12
 * @Version 1.0
 */
public class ASimpleTest {
    /**
     * 生产者
     * @throws MQClientException
     * @throws MQBrokerException
     * @throws RemotingException
     * @throws InterruptedException
     */
    @Test
    public void rocketMQStartProducer() throws MQClientException, MQBrokerException, RemotingException, InterruptedException {
        // 创建producer生产者
        DefaultMQProducer producer = new DefaultMQProducer("test-producer-group");
        producer.setNamesrvAddr(MQConstant.NAME_SRV_ADDR);
        producer.start();

        Message message = new Message();
        message.setTopic("testTopic");
        message.setBody("测试MQ的消息".getBytes(StandardCharsets.UTF_8));

        SendResult sendResult = producer.send(message);
        System.out.println(sendResult);

        // 关闭生产者
        producer.shutdown();
    }

    @Test
    public void rocketMQStartConsumer() throws MQClientException, InterruptedException {
        // 创建消费者，绑定NameServer
        DefaultMQPushConsumer consumer = new DefaultMQPushConsumer("test-consumer-group");
        consumer.setNamesrvAddr(MQConstant.NAME_SRV_ADDR);
        // 订阅主题
        // subscribe(String topic, String subExpression)
        // topic: 主题
        // subExpression: * 表示订阅这个主题中的所有消息，后面会学到消息过滤
        consumer.subscribe("testTopic", "*");
        // 设置监听器, 回调函数
        consumer.registerMessageListener(new MessageListenerConcurrently() {
            @Override
            public ConsumeConcurrentlyStatus consumeMessage(List<MessageExt> list, ConsumeConcurrentlyContext context) {
                MessageExt message = list.get(0);
                System.out.println("消息: " + message.toString());
                System.out.println("消息上下文: " + context);
                // CONSUME_SUCCESS: 消费成功，消息会从消息队列出队
                // RECONSUME_LATER: 消费失败，消息会不会出队
                // 返回NULL也代表出错，消息依旧不会从队列中移除。
                return ConsumeConcurrentlyStatus.CONSUME_SUCCESS;
            }
        });

        consumer.start();

        Thread.sleep(20000);
    }
}
