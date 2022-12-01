package com.xiaohe.fanout.publish;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.xiaohe.fanout.util.ConnectionUtil;

import java.io.IOException;

import java.util.concurrent.TimeoutException;

public class PubSub_Publish {
    public static void main(String[] args) throws IOException, TimeoutException {
        // 1. 获取连接
        Connection connection = ConnectionUtil.getConnection();
        // 2. 创建通道
        Channel channel = connection.createChannel();

        // 3. 声明交换机
        // 参数一: 交换机名称(随意指定)
        // 参数二: 交换机类型 必须是fanout
        String exchangeName = "my_fanout_exchange";
        channel.exchangeDeclare(exchangeName, "fanout");


        String message = " hello rabbitMQ!";
        channel.basicPublish(exchangeName, "", null, message.getBytes());

        // 5. 关闭资源
        channel.close();
        connection.close();
    }
}