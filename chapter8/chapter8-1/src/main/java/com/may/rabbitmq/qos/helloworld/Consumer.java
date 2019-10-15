package com.may.rabbitmq.qos.helloworld;

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
        String qosExchangeName = "qos_exchange_name";
        String exchangeType = "direct";
        String qosRoutingKey = "qos_routingKey";
        String qosQueueName = "qos_queue";

        // 声明一个交换机
        channel.exchangeDeclare(qosExchangeName, exchangeType, true, false, null);

        // 声明一个队列
        channel.queueDeclare(qosQueueName, true, false, false, null);

        // 绑定关系（队列、交换机、路由键）
        channel.queueBind(qosQueueName, qosExchangeName, qosRoutingKey);

        // 创建消费者
        // springboot 从 1.5.9 升级到 2.0.0，QueueingConsumer 报错（Cannot resolve symbol 'QueueingConsumer'）没有这个类，改为使用 DefaultConsumer
        MyConsumer myConsumer = new MyConsumer(channel);

        // 设置限流 prefetchCount 表示每次处理多少条
        // void BasicQos(uint prefetchSize, ushort prefetchCount, bool global);
        channel.basicQos(0, 1, false);

        // 设置 channel autoAck 限流模式一定要设置为 false
        channel.basicConsume(qosQueueName, false, myConsumer);

        System.out.println("消费端启动成功！");
    }
}
