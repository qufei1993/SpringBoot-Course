package com.may.rabbitmq.consumerdefault.helloworld;

import com.rabbitmq.client.*;

public class Consumer {
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

        // 定义信息
        String directExchangeName = "direct_exchange_name";
        String exchangeType = "direct";
        String directRoutingKey = "direct_routingKey";
        String directQueueName = "direct_queue";

        // 声明一个交换机
        channel.exchangeDeclare(directExchangeName, exchangeType, true, false, null);

        // 声明一个队列
        channel.queueDeclare(directQueueName, true, false, false, null);

        // 绑定关系（队列、交换机、路由键）
        channel.queueBind(directQueueName, directExchangeName, directRoutingKey);

        // 创建消费者
        // springboot 从 1.5.9 升级到 2.0.0，QueueingConsumer 报错（Cannot resolve symbol 'QueueingConsumer'）没有这个类，改为使用 DefaultConsumer
        MyConsumer myConsumer = new MyConsumer(channel);

        // 6. 设置 channel
        channel.basicConsume(directQueueName, true, myConsumer);

        System.out.println("消费端启动成功！");
    }
}
