package com.may.rabbitmq.dlx.helloworld;

import com.rabbitmq.client.*;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
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

        // 定义正常交换机、队列等信息
        String dlxExchangeName = "dlx_exchange_name";
        String exchangeType = "direct";
        String dlxRoutingKey = "dlx_routingKey";
        String dlxQueueName = "dlx_queue";

        // 定义死信队列交换机、队列等信息
        String dlxTestExchangeName = "dlx_test_exchange_name";
        String dlxTestRoutingKey = "dlx_test_routingKey";
        String dlxTestQueueName = "dlx_test_queue";

        // 声明一个正常的交换机、队列和绑定关系
        channel.exchangeDeclare(dlxExchangeName, exchangeType, true, false, null);
        // 声明死信队列交换机
        Map<String, Object> arguments = new HashMap<String, Object>();
        arguments.put("x-dead-letter-exchange", dlxTestExchangeName);
        channel.queueDeclare(dlxQueueName, true, false, false, arguments);
        channel.queueBind(dlxQueueName, dlxExchangeName, dlxRoutingKey);


        // 死信队列的交换机、队列声明和绑定关系
        channel.exchangeDeclare(dlxTestExchangeName, "direct", true, false, null);
        channel.queueDeclare(dlxTestQueueName, true, false, false, null);
        channel.queueBind(dlxTestQueueName, dlxTestExchangeName, dlxRoutingKey);

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
            }
        };

        // 6. 设置 channel
        channel.basicConsume(dlxTestQueueName, true, consumer);

        System.out.println("消费端启动成功！");
    }
}