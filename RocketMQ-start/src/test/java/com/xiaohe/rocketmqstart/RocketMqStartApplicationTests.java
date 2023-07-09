package com.xiaohe.rocketmqstart;

import com.xiaohe.rocketmqstart.constants.MQConstant;
import org.apache.rocketmq.client.exception.MQBrokerException;
import org.apache.rocketmq.client.exception.MQClientException;
import org.apache.rocketmq.client.producer.DefaultMQProducer;
import org.apache.rocketmq.client.producer.SendResult;
import org.apache.rocketmq.common.message.Message;
import org.apache.rocketmq.remoting.exception.RemotingException;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.nio.charset.StandardCharsets;

@SpringBootTest
class RocketMqStartApplicationTests {

	/**
	 * 发送消息测试
	 */
	@Test
	void contextLoads() throws MQClientException, MQBrokerException, RemotingException, InterruptedException {
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

}
