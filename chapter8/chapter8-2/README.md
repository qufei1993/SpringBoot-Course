# SpringBoot 整合 RabbitMQ

## SimpleMessageListenerContainer

SimpleMessageListenerContainer 是一个简单的消息监听容器，用于消费者的配置项

## 添加maven依赖

在 ```Spring Boot``` 项目的 ```pom.xml``` 文件中引入 ```amqp-client``` 启动器

```xml
<dependency>
    <groupId>com.rabbitmq</groupId>
    <artifactId>amqp-client</artifactId>
    <version>5.6.0</version>
</dependency>
```

[Github 查看本文完整示例 chapter8-1](https://github.com/Q-Angelo/SpringBoot-Course/tree/master/chapter8/chapter8-1)
