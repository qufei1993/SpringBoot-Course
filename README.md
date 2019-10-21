# SpringBoot实战系列

本项目主要基于Spring Boot框架从零开始，从最开始的项目构建、项目配置、数据存储等渐进式的进行讲解，每个章节的讲解都有之对应的源码。如果能对您有帮助，欢迎点击右上角Star按钮，给予支持！

> **作者：** 五月君，Node.js Developer，[慕课网认证作者](https://www.imooc.com/u/2667395)。

## 宗旨

* 小项目大思想
* 以最为简洁的代码示例进行讲解
* 每一篇讲解都附带源码地址

## 项目构建

* [IntelliJ IDEA 中的Spring Initializr快速构建SpringBoot工程](/chapter1/README.md#intellig编辑器创建)
* [编写一个Hello SpringBoot程序](/chapter1/README.md#编写一个hello-springboot-程序)
     - `[运行程序]` 三种方式启动项目 [`[more]`](/chapter1/README.md#三种启动方式)
* [项目属性配置](/chapter1/README.md#项目属性配置)
    - `[项目属性配置]` application.properties文件设置配置 [`[more]`](/chapter1/README.md#后缀properties文件配置)
    - `[项目属性配置]` application.yml文件设置配置 [`[more]`](/chapter1/README.md#后缀yml文件配置)
    - `[项目属性配置]` 自定义属性配置参数间引用 [`[more]`](/chapter1/README.md#自定义属性配置及参数间引用)
    - `[项目属性配置]` 多环境动态配置 [`[more]`](/chapter1/README.md#多环境动态配置)

## 数据存储

- ### 概览
> 在介绍以下几种数据库之前少不了先说下```Spring```家族的```spring-data```，适用于关系型和非关系型数据库，简化了配置和数据库访问。例如，```Spring Data JPA```、```Spring Data MongoDB```、```Spring Data Redis```等
- ### MySql
    * [Spring-Data-Jpa简介及常用CRUD方法](/chapter2/README.md#常用方法)
    * [pom.xml增加依赖](/chapter2/README.md#添加依赖)
    * [修改配置文件 数据库Mysql、Jpa相关配置](/chapter2/README.md#mysql相关配置)
    * [Spring-Data-Jpa实现CRUD操作实例](/chapter2/README.md#实例)
    * [问题排错](/chapter2/README.md#问题排错)
- ### MongoDB
    * [MongoDB、Spring Data MongoDB简介](/chapter2/README.md#简介)
    * [pom.xml增加spring-boot-starter-data-mongodb依赖](/chapter2/README.md#添加mongodb依赖)
    * [修改配置文件 数据库MongoDB相关配置](/chapter2/README.md#修改配置文件mongodb相关配置)
    * [定义集合模型](/chapter2/README.md#定义集合模型)
    * [创建数据访问对象](/chapter2/README.md#创建继承于mongorepository的数据访问对象)
    * [创建实例实现对数据的增删改查操作](/chapter2/README.md#创建控制层实现对数据的增删改查)
- ### Redis

## AOP面向切面编程

> AOP是一种与语言无关的程序思想、编程范式。项目业务逻辑中，将通用的模块以水平切割的方式进行分离统一处理，常用于日志、权限控制、异常处理等业务中。

* [引入AOP依赖](/chapter3/README.md#引入aop依赖)
* [AOP常用注解解析](/chapter3/README.md#aop注解)
* [实现日志分割功能](/chapter3/README.md#实现日志分割功能)
    * [```@Pointcut``` 添加切入点](/chapter3/README.md#添加切入点)
    * [```@Before``` 前置通知](/chapter3/README.md#前置通知)
    * [```@After``` 后置通知](/chapter3/README.md#后置通知)
    * [```@Around``` 环绕通知](/chapter3/README.md#环绕通知)
    * [```@AfterReturning``` 返回后通知](/chapter3/README.md#返回后通知)
    * [```@AfterReturning``` 异常通知](/chapter3/README.md#异常通知)
* [一段段伪代码读懂执行顺序](/chapter3/README.md#一段段伪代码读懂执行顺序)
* [对正常、异常两种情况分别进行测试](/chapter3/README.md测试正常异常两种情况)

## 统一异常处理

* [统一返回数据结构](/chapter4/README.md#统一返回数据结构)
    * `[统一返回数据结构]` 定义接口返回数据结构
    * `[统一返回数据结构]` 数据接口字段模型定义
    * `[统一返回数据结构]` 封装接口返回方法（成功、失败）
* [统一异常处理](/chapter4/README.md#统一异常处理)
    * `[统一异常处理]` 状态消息枚举
    * `[统一异常处理]` 自定义异常类
    * `[统一异常处理]` @ControllerAdvice统一处理异常
* [测试](/chapter4/README.md#测试)
    * `[测试]` 测试正常返回及空指针系统异常
    * `[测试]` 自定义异常测试

## 单元测试

* [添加Maven依赖](/chapter5/README.md#添加maven依赖)
* [借助IntelliJ IDEA编辑器快速创建测试类](/chapter5/README.md#创建测试类)
* [Service单元测试](/chapter5/README.md#service单元测试)
* [Controller单元测试](/chapter5/README.md#controller单元测试)
* [问题汇总](/chapter5/README.md#问题汇总)

## 邮件发送

> 本篇主要介绍了Spring Boot中邮件发送，分别讲解了简单的文本邮件、HTML邮件、附件邮件、图片邮件、模板邮件。

* [添加Maven依赖](/chapter6/README.md#添加maven依赖)
* [配置文件增加邮箱相关配置](/chapter6/README.md#配置文件增加邮箱相关配置)
* [Service、Test项目代码构建](/chapter6/README.md#项目构建)
* [五种邮件发送类型讲解](/chapter6/README.md#五种邮件发送类型讲解)
    * [文本邮件](/chapter6/README.md#文本邮件)
    * [html邮件](/chapter6/README.md#html邮件)
    * [附件邮件](/chapter6/README.md#附件邮件)
    * [html内嵌图片邮件](/chapter6/README.md#html内嵌图片邮件)
    * [模板邮件](/chapter6/README.md#html内嵌图片邮件)
* [问题汇总](/chapter6/README.md#问题汇总)

## SpringBoot集成Consul

- ### 微服务服务注册发现之 Consul 系列 [[more]](https://github.com/Q-Angelo/Nodejs-Roadmap/blob/master/docs/microservice/consul.md)
    - ```[Consul]``` [使用Consul解决了哪些问题](https://github.com/Q-Angelo/Nodejs-Roadmap/blob/master/docs/microservice/consul.md#使用consul解决了哪些问题)
    - ```[Consul]``` [微服务Consul系列之服务部署、搭建、使用](https://github.com/Q-Angelo/Nodejs-Roadmap/blob/master/docs/microservice/consul.md#consul架构)
    - ```[Consul]``` [微服务Consul系列之集群搭建](https://github.com/Q-Angelo/Nodejs-Roadmap/blob/master/docs/microservice/consul.md#集群搭建)
    - ```[Consul]``` [微服务Consul系列之服务注册与服务发现](https://github.com/Q-Angelo/Nodejs-Roadmap/blob/master/docs/microservice/consul.md#服务注册与发现)
    - ```[Question]``` [微服务Consul系列之问题汇总篇](https://github.com/Q-Angelo/Nodejs-Roadmap/blob/master/docs/microservice/consul.md#问题总结)

- ### SpringBoot集成Consul配置中心
    * [添加 Consul Maven依赖](/chapter7/README.md#添加maven依赖)
    * [系统级配置文件&应用级配置文件](/chapter7/README.md#配置文件)
    * [配置Consul管理控制台](/chapter7/README.md#配置Consul管理控制台)
    * [项目构建](/chapter7/README.md#项目构建)
        * [建立Config获取Consul配置数据](/chapter7/README.md#建立Config获取Consul配置数据)
        * [编写启动类调用配置](/chapter7/README.md#编写启动类调用配置)
    * [接口测试](/chapter7/README.md#接口测试)
    * [总结](/chapter7/README.md#总结)

## 消息中间件 RabbitMQ

- ### 消息中间件 RabbitMQ 基础篇
    - [RabbitMQ：入门篇](https://github.com/Q-Angelo/Nodejs-Roadmap/blob/master/docs/microservice/rabbitmq-base.md)
    - [RabbitMQ：交换机消息投递机制](chapter8/chapter8-1/README.md)
    - [RabbitMQ：死信队列+TTL 实现定时任务](https://github.com/Q-Angelo/Nodejs-Roadmap/blob/master/docs/microservice/rabbitmq-schedule.md)
    - [RabbitMQ：高并发下消费端限流实践](https://github.com/Q-Angelo/Nodejs-Roadmap/blob/master/docs/microservice/rabbitmq-prefetch.md)
    - [RabbitMQ：服务异常重连](https://github.com/Q-Angelo/Nodejs-Roadmap/blob/master/docs/microservice/rabbitmq-reconnecting.md)
- ### 消息中间件 RabbitMQ 框架整合

## 转载分享

建立本开源项目的初衷是基于个人学习与工作中对 Spring Boot 的总结记录，在这里也希望能帮助一些在学习 Spring Boot 过程中遇到问题的小伙伴，如果您需要转载本仓库的一些文章到自己的博客，请按照以下格式注明出处，谢谢合作。

```
作者：五月君
链接：https://github.com/Q-Angelo/SpringBoot-Course
来源：Github SpringBoot实战系列
```

## 参与贡献

1. 如果您对本项目有任何建议或发现文中内容有误的，欢迎提交 issues 进行指正。
2. 对于文中我没有涉及到知识点，欢迎提交 PR。
3. 如果您有文章推荐请以 markdown 格式到邮箱 `qzfweb@gmail.com`，[中文技术文档的写作规范指南](https://github.com/ruanyf/document-style-guide)。

## 联系我

- **微信**
添加我的微信备注“`Spring Boot`”
<img src="./img/wx.jpeg" width="180" height="180"/>

- **公众号**
专注于Node.js相关技术栈的研究分享，包括基础知识、Nodejs、Consul、Redis、微服务、消息中间件等，如果大家感兴趣可以给予关注支持！
<img src="./img/node_roadmap_wx.jpg" width="180" height="180"/>

<hr/>

#### 未完待续，持续更新中。。。