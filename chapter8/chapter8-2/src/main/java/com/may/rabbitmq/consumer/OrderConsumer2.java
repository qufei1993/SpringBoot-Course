package com.may.rabbitmq.consumer;

import com.rabbitmq.client.Channel;
import lombok.Data;
import org.springframework.amqp.rabbit.annotation.*;
import org.springframework.amqp.support.AmqpHeaders;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.messaging.Message;
import org.springframework.stereotype.Component;

@Configuration
@Component
public class OrderConsumer2 {

    @RabbitListener(bindings = @QueueBinding(
            value = @Queue(value = "order_direct_exchange", durable = "true"),
            exchange = @Exchange(value = "order_direct_queue", durable = "true", type = "direct", ignoreDeclarationExceptions = "true"),
            key = "order_routingKey"
    ))
    @RabbitHandler
    public void onMessage(Message message, Channel channel) throws Exception {
        System.err.println("OrderConusmer2: " + message.getPayload());

        Long deliveryTag = (Long)message.getHeaders().get(AmqpHeaders.DELIVERY_TAG);

        channel.basicAck(deliveryTag, false);
    }
}
