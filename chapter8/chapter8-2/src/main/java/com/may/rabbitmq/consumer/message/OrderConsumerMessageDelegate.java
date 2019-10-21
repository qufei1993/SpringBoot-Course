package com.may.rabbitmq.consumer.message;

import java.util.Map;

public class OrderConsumerMessageDelegate {
    public void orderConsumerMsg(byte[] messageBody) {
        System.out.println("orderConsumerMsg：" + new String(messageBody));
    }

    //    public void handleMessage(byte[] messageBody) {
    //        System.out.println("handleMessage：" + new String(messageBody));
    //    }

    public void jsonOrderConsumerMsg(Map messageBody) {
        System.out.println("jsonOrderConsumerMsg：" + messageBody);
    }
}
