package com.may.rabbitmq.confirm.helloworld;

import com.rabbitmq.client.*;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

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
        String confirmExchangeName = "confirm_exchange_name";
        String exchangeType = "direct";
        String confirmRoutingKey = "confirm_routingKey";
        String confirmQueueName = "confirm_queue";

        // 声明一个交换机
        channel.exchangeDeclare(confirmExchangeName, exchangeType, true, false, null);

        // 声明一个队列
        channel.queueDeclare(confirmQueueName, true, false, false, null);

        // 绑定关系（队列、交换机、路由键）
        channel.queueBind(confirmQueueName, confirmExchangeName, confirmRoutingKey);

        // 创建消费者
        // springboot 从 1.5.9 升级到 2.0.0，QueueingConsumer 报错（Cannot resolve symbol 'QueueingConsumer'）没有这个类，改为使用 DefaultConsumer
        DefaultConsumer consumer = new DefaultConsumer(channel) {
            @Override
            public void handleDelivery(String consumerTag, Envelope envelope, AMQP.BasicProperties properties, byte[] body) throws IOException {
                super.handleDelivery(consumerTag, envelope, properties, body);

                String message = new String(body, "UTF-8");
                System.out.printf("in consumer B (delivery tag is %d): %s\n", envelope.getDeliveryTag(), message);

                try {
                    TimeUnit.MILLISECONDS.sleep(200);
                } catch (InterruptedException e) {

                }

                channel.basicAck(envelope.getDeliveryTag(), false);
            }
        };

        // 6. 设置 channel
        channel.basicConsume(confirmQueueName, false, consumer);

        System.out.println("消费端启动成功！");
    }
}