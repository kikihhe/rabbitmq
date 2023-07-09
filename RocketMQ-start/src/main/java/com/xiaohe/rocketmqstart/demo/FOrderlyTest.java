package com.xiaohe.rocketmqstart.demo;

import com.xiaohe.rocketmqstart.constants.MQConstant;
import com.xiaohe.rocketmqstart.entity.Order;
import org.apache.rocketmq.client.exception.MQBrokerException;
import org.apache.rocketmq.client.exception.MQClientException;
import org.apache.rocketmq.client.producer.DefaultMQProducer;
import org.apache.rocketmq.client.producer.MessageQueueSelector;
import org.apache.rocketmq.common.message.Message;
import org.apache.rocketmq.common.message.MessageQueue;
import org.apache.rocketmq.remoting.exception.RemotingException;
import org.junit.Test;

import java.nio.charset.StandardCharsets;
import java.util.List;

/**
 * @ClassName FOrderlyTest
 * @Description 顺序消息
 * @Author 何
 * @Date 2023-07-09 22:20
 * @Version 1.0
 */
public class FOrderlyTest {
    @Test
    public void produce() throws MQClientException, MQBrokerException, RemotingException, InterruptedException {
        DefaultMQProducer producer = new DefaultMQProducer("orderly-produce-group");
        producer.setNamesrvAddr(MQConstant.NAME_SRV_ADDR);
        producer.start();

        Message message = new Message();
        message.setTopic("testTopic");
        Order order = new Order(1, "苹果");
        message.setBody(order.toString().getBytes(StandardCharsets.UTF_8));
        producer.send(message, new MessageQueueSelector() {
            // mqs: 该topic中的所有队列
            // arg: 选择的ShardingKey, 此处为 orderId
            @Override
            public MessageQueue select(List<MessageQueue> mqs, Message msg, Object arg) {
                int index = ((Integer)arg) % mqs.size();
                // 返回值是 这个消息往哪个队列中投放
                return mqs.get(index);
            }
        }, order.getId());

    }
}
