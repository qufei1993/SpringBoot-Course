package com.may.rabbitmq.confirm.helloworld;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.ConfirmListener;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;

import java.io.IOException;

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

        // 指定消息确认模式
        channel.confirmSelect();

        // 通过 channel 发送数据
        // exchange：交换机，如果不传默认为 AMQP default
        String confirmExchangeName = "confirm_exchange_name";
        String confirmRoutingKey = "confirm_routingKey";
        String confirmMsg = "confirm hello world";

        channel.basicPublish(confirmExchangeName, confirmRoutingKey, null, confirmMsg.getBytes());

        channel.addConfirmListener(new ConfirmListener() {
            @Override
            public void handleAck(long l, boolean b) throws IOException {
                System.out.println("--------handleAck-----------");
                System.out.print(l);
                System.out.print(b);
            }

            @Override
            public void handleNack(long l, boolean b) throws IOException {
                System.out.println("--------handleNack-----------");
                System.out.print(l);
                System.out.print(b);
            }
        });

        // 关闭链接
        // channel.close();
        // connection.close();
    }
}
