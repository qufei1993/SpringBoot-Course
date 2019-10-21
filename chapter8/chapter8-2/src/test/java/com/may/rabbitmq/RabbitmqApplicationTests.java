package com.may.rabbitmq;

import com.may.rabbitmq.Producer.OrderProducer;
import com.may.rabbitmq.consumer.OrderConsumer;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.HashMap;
import java.util.Map;

@RunWith(SpringRunner.class)
@SpringBootTest
public class RabbitmqApplicationTests {

    @Test
    public void contextLoads() {
    }

    @Autowired
    private OrderProducer orderProducer;

    @Test
    public void testOrderSender01() throws Exception {
        Map<String, Object> params = new HashMap<String, Object>();

        params.put("name", "测试订单");
        params.put("orderId", "123456");

        orderProducer.send("hello world", params);

        Thread.sleep(2000);

        System.err.println("消息发送测试");
    }
}
