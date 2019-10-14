package com.may.rabbitmq.fanout.helloworld;

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
        String fanoutExchangeName = "fanout_exchange_name";
        String fanoutRoutingKey = "";
        String fanoutMsg = "fanout hello world";

        channel.basicPublish(fanoutExchangeName, fanoutRoutingKey, null, fanoutMsg.getBytes());

        // 关闭链接
        channel.close();
        connection.close();
    }
}
