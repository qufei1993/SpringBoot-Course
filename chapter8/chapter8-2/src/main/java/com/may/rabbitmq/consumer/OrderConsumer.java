package com.may.rabbitmq.consumer;

import com.may.rabbitmq.consumer.message.OrderConsumerMessage;
import com.may.rabbitmq.consumer.message.OrderConsumerMessageDelegate;
import lombok.Data;
import org.springframework.amqp.core.*;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.listener.SimpleMessageListenerContainer;
import org.springframework.amqp.rabbit.listener.adapter.MessageListenerAdapter;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Data
@Configuration
@EnableAutoConfiguration
@ConfigurationProperties(prefix = "order")
public class OrderConsumer {
    private String directQueueName;

    @Value("${order.routingKey}")
    private String routingKey;

    // 声明队列
    @Bean
    public Queue orderDirectQueue() {
        return new Queue(directQueueName);
    }

    @Bean
    public Binding orderDirectBinding(DirectExchange orderDirectExchange) {
        return BindingBuilder.bind(orderDirectQueue()).to(orderDirectExchange).with(routingKey);
    }

    @Bean
    public SimpleMessageListenerContainer simpleMessageListenerContainer(ConnectionFactory connectionFactory) {
        SimpleMessageListenerContainer container = new SimpleMessageListenerContainer(connectionFactory);

        // 监听队列，可以同时设置多个
        container.setQueues(orderDirectQueue());

        // 设置当前的消费者数量
        container.setConcurrentConsumers(2);

        // 设置最大的消费者数量
        container.setMaxConcurrentConsumers(10);

        // 设置重回队列，false 不会重回队列
        container.setDefaultRequeueRejected(false);

        // 设置签收模式为手动签收，自动签收为 AcknowledgeMode.AUTO
        // container.setAcknowledgeMode(AcknowledgeMode.MANUAL); // 手动签收
        container.setAcknowledgeMode(AcknowledgeMode.AUTO);

        // 监听消费端方法一
        OrderConsumerMessage orderConsumerMessage = new OrderConsumerMessage();
        container.setMessageListener(orderConsumerMessage);

        // 监听消费端方法二
        //        container.setMessageListener(new ChannelAwareMessageListener() {
        //            @Override
        //            public void onMessage(Message message, Channel channel) throws Exception {
        //                String msg = new String(message.getBody());
        //
        //                System.out.println("----------- Message -----------");
        //                System.out.println(msg);
        //            }
        //        });

        // 监听消费端方法三 | 一
        // 适配器方式
        //        MessageListenerAdapter adapter = new MessageListenerAdapter(new OrderConsumerMessageDelegate());
        //        adapter.setDefaultListenerMethod("orderConsumerMsg"); // 自定义适配器方法名
        //        container.setMessageListener(adapter);

        // 监听消费端方法三 | 二
        // 适配器方式
        //        MessageListenerAdapter adapter = new MessageListenerAdapter(new OrderConsumerMessageDelegate());
        //        adapter.setDefaultListenerMethod("jsonOrderConsumerMsg"); // 自定义适配器方法名
        //
        //        Jackson2JsonMessageConverter jackson2JsonMessageConverter = new Jackson2JsonMessageConverter();
        //        adapter.setMessageConverter(jackson2JsonMessageConverter); // 消息转为 json
        //
        //        container.setMessageListener(adapter);

        System.out.println("消费端重启成功");

        return container;
    }
}
