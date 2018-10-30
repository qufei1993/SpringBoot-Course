# SpringBoot实战系列

本项目主要基于Spring Boot框架从零开始，从最开始的项目构建、项目配置、数据存储等渐进式的进行讲解，每个章节的讲解都有之对应的源码。如果能对您有帮助，欢迎点击右上角Star按钮，给予支持！

## 宗旨

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
    * [MongoDB、Spring Data MongoDB简介](#简介)
    * [pom.xml增加spring-boot-starter-data-mongodb依赖](#添加依赖)
    * [修改配置文件数据MongoDB相关配置](#修改配置文件)
    * [定义集合模型](#定义集合模型)
    * [创建数据访问对象](#创建数据访问对象)
    * [创建实例实现对数据的增删改查操作](#创建控制层实现对数据的增删改查)
- ### Redis

#### 未完待续，持续更新中。。。