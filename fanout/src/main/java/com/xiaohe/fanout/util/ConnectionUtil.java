package com.xiaohe.fanout.util;

import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

/**
 * @author : 小何
 * @Description :
 * @date : 2022-12-01 15:15
 */
public class ConnectionUtil {
    private static Connection connection;
    public static Connection getConnection() throws IOException, TimeoutException {
        // 1.建立连接
        ConnectionFactory factory = new ConnectionFactory();
// 1.1.设置连接参数，分别是：主机名、端口号、vhost、用户名、密码
        factory.setHost("192.168.93.139");
        factory.setPort(5672);
        factory.setVirtualHost("/");
        factory.setUsername("xiaohe");
        factory.setPassword("123456");
// 1.2.建立连接
        Connection connection = factory.newConnection();
        return connection;
    }
}
