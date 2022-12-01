package com.xiaohe.fanout.consume;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;


import java.io.IOException;
import java.util.concurrent.TimeoutException;

/**
 * @author : 小何
 * @Description :
 * @date : 2022-12-01 15:17
 */

import com.rabbitmq.client.*;
import com.xiaohe.fanout.util.ConnectionUtil;


/**
 * @author : 小何
 * @Description :
 * @date : 2022-11-20 18:26
 */

public class Consume1 {
    public static void main(String[] args) throws IOException, TimeoutException {
        // 1.建立连接
        Connection connection = ConnectionUtil.getConnection();
        // 2.创建通道Channel
        Channel channel = connection.createChannel();

        // 3. 声明交换机
        String exchangeName = "my_fanout_exchange";
        channel.exchangeDeclare(exchangeName, "fanout");

        // 4.声明队列
        String queueName = "fanout.queue";
        channel.queueDeclare(queueName, false, false, false, null);

        // 5. 将队列绑定到交换机上
        channel.queueBind(queueName, exchangeName, "");


        // 这里只是声明一个回调函数，并不是真正执行，等消息过来才会执行,而且是新的线程执行。
        // 其他线程执行回调函数。主线程不阻塞等待.
        DefaultConsumer consume = new DefaultConsumer(channel) {
            @Override
            public void handleDelivery(String consumerTag,
                                       Envelope envelope,
                                       AMQP.BasicProperties properties,
                                       byte[] body) throws IOException {
                // 处理消息
                String message = new String(body);
                System.out.println("接收到消息: " + message);
            }
        };
        // 监听队列, 订阅消息
        // 自动ACK
        channel.basicConsume(queueName, true, consume);
    }

}