package com.may.rabbitmq.topic.helloworld;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;

public class Producer {
    public static void main(String[] args) throws Exception {
        // 创建链接工厂
        ConnectionFactory connectionFactory = new ConnectionFactory();
        connectionFactory.setHost("127.0.0.1");
        connectionFactory.setPort(5672);
        connectionFactory.setVirtualHost("/");

        // 通过链接工厂创建链接
        Connection connection = connectionFactory.newConnection();

        // 通过链接创建通道（channel）
        Channel channel = connection.createChannel();

        // 通过 channel 发送数据
        // exchange：交换机，如果不传默认为 AMQP default
        String topicExchangeName = "topic_exchange_name";
        String topicRoutingKey1 = "topic_routingKey.test1";
        String topicRoutingKey2 = "topic_routingKey.test2";
        String topicRoutingKey3 = "topic_routingKey.test3.test03";
        String topicMsg = "topic hello world";

        channel.basicPublish(topicExchangeName, topicRoutingKey1, null, topicMsg.getBytes());
        channel.basicPublish(topicExchangeName, topicRoutingKey2, null, topicMsg.getBytes());
        channel.basicPublish(topicExchangeName, topicRoutingKey3, null, topicMsg.getBytes());

        // 关闭链接
        channel.close();
        connection.close();
    }
}
