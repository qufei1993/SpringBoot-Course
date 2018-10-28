# 数据存储

## 快速导航

- ### 概览[#](#概览)
- ### MySql
    * [Spring-Data-Jpa简介及常用CRUD方法](/chapter2/README.md#常用方法)
    * [pom.xml增加依赖](/chapter2/README.md#添加依赖)
    * [修改配置文件 数据库Mysql、Jpa相关配置](/chapter2/README.md#mysql相关配置)
    * [Spring-Data-Jpa实现CRUD操作实例](/chapter2/README.md#实例)
    * [问题排错](/chapter2/README.md#问题排错)
- ### MongoDB

- ### Redis

## 概览

在介绍以下几种数据库之前少不了先说下```Spring```家族的```spring-data```，适用于关系型和非关系型数据库，简化了配置和数据库访问。例如，```Spring Data JPA```、```Spring Data MongoDB```、```Spring Data Redis```等

[Spring Data 官网](https://spring.io/projects/spring-data)，推荐去官网看看。

## mysql

> Mysql数据库这里要用到Spring-Data-Jpa，它是JPA规范下提供的Repository层的实现，可以使用Hibernate、OpenJpa等框架进行开发。关于JPA规范，它的全称```Java Persistence API```(Java持久化API)一个ORM规范，具体实现还是Hibernate等，JPA为我们提供了CRUD的接口。

#### 常用方法

``` 更多详细方法及使用参考官方文档 https://docs.spring.io/spring-data/jpa/docs/current/reference/html/```

* save(): 保存、更新
* delete: 删除，或者```deleteByProperty``` Property为字段属性名
* findOne(): 通过id查询
* findByProperty(type Property): 通过属性查询，例如表中的name字段查询，实现方式 findByName(String name)
* findAll(): 查询所有数据
* findAll(new PageRequest(1, 20)): 分页

#### 添加依赖

项目根目录 ```pom.xml``` 添加依赖 ```spring-boot-starter-data-jpa``` ```mysql-connector-java```

```xml
<dependency>
    <groupId>org.springframework.boot</groupId>
    <artifactId>spring-boot-starter-data-jpa</artifactId>
</dependency>

<dependency>
    <groupId>mysql</groupId>
    <artifactId>mysql-connector-java</artifactId>
</dependency>
```

#### 修改配置文件

``` application.yml ```

* ##### mysql相关配置

* ```datasource``` 数据源
    * ```driver-class-name``` 驱动名称
    * ```url`` 数据库地址（hots:port/database）
    * ```username``` 数据库用户名
    * ```password```  数据库密码

```yml
spring:
    datasource:
        driver-class-name: com.mysql.jdbc.Driver
        url: jdbc:mysql://127.0.0.1:3306/dbUser?useUnicode=true&characterEncoding=utf-8&useSSL=true
        username: root
        password: 123456
```

* ##### jpa相关配置

* ```hibernate```: 相关配置信息有以下几种类型
    * ```ddl-auto:create```: 每次运行加载不管之前是否有数据都会自动创建一个表，会造成数据丢失。
    * ```ddl-auto:update```: 第一次加载会创建新的数据接口，之后只会在原有表基础之上进行迭代。
    * ```ddl-auto:validate```: 验证类里面的属性与表结构是否一致。
    * ```ddl-auto:create-drop```: 每次退出时删除。
    * ```ddl-auto:node```: 默认什么都不做。
* ```show-sql```: 是否打印SQL，在开发时可以开启方便调试。
* ```database```: 数据库类型。

```yml
spring:
    jpa:
        hibernate:
            ddl-auto: update
        show-sql: true
        database: mysql

```

#### 实例
#### Spring-Data-Jpa实现CRUD操作

实现以下需求：

* ```GET```: 查询所有用户信息
* ```GET```: 根据年龄获取用户信息
* ```POST```: 增加用户(姓名、年龄)
* ```PUT```: 修改用户
* ```DELETE```: 删除用户

##### 创建表

就是创建存储的User实体（User类）

> 是不需要手动去数据库创建表的，以下创建的User类和定义的属性会对应到数据库中的表和字段，这就需要应用jpa的特性了，看下以下注解。

* ```@Entity```: 代表此类映射为数据库的表结构
* ```@Id```: 指定一个主键
* ```@GeneratedValue```: 配置主键相关信息
    * ```Table```: 使用一个特定的数据库表来保存主键
    * ```IDENTITY```: 数据库自动生成
    * ```AUTO```: 主键由程序控制，默认值
    * ```SEQUENCE```: 通过数据库的序列产生主键, ```MYSQL```不支持，部分数据库```(Oracle,PostgreSQL,DB2)```支持序列对象

```User.java```

```java
@Entity
public class User {

    @Id
    @GeneratedValue
    private Integer id;

    private String name;

    private Integer age;

    public User() {
    }

    public Integer getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public Integer getAge() {
        return age;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setAge(Integer age) {
        this.age = age;
    }
}

```

##### 创建数据访问接口

创建接口User的数据访问UserRepository继承于JpaRepository，可以在这个接口里实现UserRepository的扩展

``` UserRepository.java ```

```java

public interface UserRepository extends JpaRepository<User, Integer> {

    /**
     * 扩展，通过名字查询
     * @param name
     * @return
     */
    public List<User> findByName(String name);
}
```

##### 创建UserController

```UserController.java```

```java
package com.angelo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
public class UserController {

    @Autowired
    private UserRepository userRepository;
}

```

* 保存一个用户

```java
@RestController
public class UserController {
    // ... 以上内容忽略
    /**
     * 保存一个用户
     * @param name
     * @param age
     * @return
     */
    @PostMapping(value = "/user")
    public User userAdd(@RequestBody User userParams) {
        User user = new User();
        user.setName(userParams.getName());
        user.setAge(userParams.getAge());

        return userRepository.save(user);
    }
}
```

postman测试

```
curl -X POST \
  http://127.0.0.1:8080/user \
  -H 'cache-control: no-cache' \
  -H 'content-type: application/json' \
  -H 'postman-token: e0832f99-8a03-e260-4388-b3f60dc2d0c4' \
  -d '{
	"name": "张三",
	"age": 18
}'
```

返回

```json
{
    "id": 3,
    "name": "张三",
    "age": 18
}
```

* 查询用户列表
```java
@RestController
public class UserController {
    // ... 以上内容忽略
    /**
     * 查询用户列表
     * @return
     */
    @RequestMapping(value = "/user/list")
    public List<User> userList() {
        return userRepository.findAll();
    }
}
```

postman测试

```
curl -X GET \
  http://127.0.0.1:8080/user/list \
  -H 'cache-control: no-cache' \
  -H 'postman-token: 1fca1e6c-820e-b5bd-952f-2ab658b084a5'
```

返回数据

```json
[
    {
        "id": 1,
        "name": "张三",
        "age": 18
    }
]
```

* 根据id查找一个用户

注意， spring-data-jpa 2.0.5.RELEASE 版本之后获取单个对象的数据源需要用```findById()```，SpringBoot1.x版本可以使用```findOne()```

```java
@RestController
public class UserController {
    // ... 以上内容忽略
    /**
     * 根据id查找一个用户
     * @param id
     * @return
     */
    @RequestMapping(value = "/user/{id}")
    public Optional<User> userFindOne(@PathVariable("id") Integer id) {
        return userRepository.findById(id);
    }
}
```

postman测试

```
curl -X GET \
  http://127.0.0.1:8080/user/1 \
  -H 'cache-control: no-cache' \
  -H 'postman-token: 801e22f4-73a1-6f1d-4207-0079d5a31004'
```

返回数据

```json
{
    "id": 1,
    "name": "张三",
    "age": 18
}
```
* 根据name查找用户

```java
@RestController
public class UserController {
    // ... 以上内容忽略
    /**
     * 根据name获取用户信息
     * @param name
     * @return
     */
    @RequestMapping(value = "/user/name", method = RequestMethod.GET)
    public List<User> findUserListByName(@RequestParam(name="name",defaultValue="") String name) {
        return userRepository.findByName(name);
    }
}
```

postman测试

```
curl -X GET \
  'http://127.0.0.1:8080/user/name?name=%E5%BC%A0%E4%B8%89' \
  -H 'cache-control: no-cache' \
  -H 'postman-token: 4b4a0850-50f5-3fb2-7137-a44f555e9b49'
```

返回数据

```json
[
    {
        "id": 1,
        "name": "张三",
        "age": 18
    }
]
```

* 更新用户信息

```java
@RestController
public class UserController {
    // ... 以上内容忽略
    /**
     * 更新用户信息
     * @param id
     * @param name
     * @param age
     * @return
     */
    @PutMapping(value = "/user/{id}")
    public User userUpdate(
            @PathVariable("id") Integer id,
            @RequestParam("name") String name,
            @RequestParam("age") Integer age
    ) {
        User user = new User();
        user.setId(id);
        user.setName(name);
        user.setAge(age);

        return userRepository.save(user);
    }
}
```

postman测试

```
curl -X PUT \
  http://127.0.0.1:8080/user/1 \
  -H 'cache-control: no-cache' \
  -H 'content-type: application/x-www-form-urlencoded' \
  -H 'postman-token: 2b717e08-8c07-2dc7-c592-81358617625b' \
  -d 'name=%E6%9D%8E%E5%9B%9B&age=20'
```

返回数据

```json
{
    "id": 1,
    "name": "李四",
    "age": 20
}
```

* 删除一个用户信息

```java
@RestController
public class UserController {
    // ... 以上内容忽略
    /**
     * 删除一个用户信息
     * @param id
     */
    @DeleteMapping(value = "/user/{id}")
    public void deleteUser(@PathVariable("id") Integer id) {
        userRepository.deleteById(id);
    }
}
```

postman测试，删除数据返回为空

```
curl -X DELETE \
  http://127.0.0.1:8080/user/1 \
  -H 'cache-control: no-cache' \
  -H 'postman-token: 47e13a68-b69a-bf7b-b14c-94ce82865496'
```

#### 问题排错

* 问题1: 
配置datasource可能报以下错误，这是因为添加了数据库依赖，autoconfig会读取数据源配置，因为```新建的项目没有配置数据源（问题重点所在）```因此抛此异常。

```shell
Description:

Failed to configure a DataSource: 'url' attribute is not specified and no embedded datasource could be configured.

Reason: Failed to determine a suitable driver class


Action:

Consider the following:
	If you want an embedded database (H2, HSQL or Derby), please put it on the classpath.
	If you have database settings to be loaded from a particular profile you may need to activate it (no profiles are currently active).


Process finished with exit code 1
```

在启动类的```@SpringBootApplication```注解上加上```exclude= {DataSourceAutoConfiguration.class}```，将会解除自动加载DataSourceAutoConfiguration。同样还会引发另外一个问题，例如本实例中配置文件里的数据库就不会自动去创建链接。

```java
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;

@SpringBootApplication(exclude= {DataSourceAutoConfiguration.class})
public class UserApplication { // 启动类

    public static void main(String[] args) {
        SpringApplication.run(UserApplication.class, args);
    }
}

```

* 问题2:

链接mysql，启动时候警告以下内容，原因是MySql高版本需要指明是否进行SSL链接

<font color="#dd0000">
WARN: Establishing SSL connection without server's identity verification is not recommended. According to MySQL 5.5.45+, 5.6.26+ and 5.7.6+ requirements SSL connection must be established by default if explicit option isn't set. For compliance with existing applications not using SSL the verifyServerCertificate property is set to 'false'. You need either to explicitly disable SSL by setting useSSL=false, or set useSSL=true and provide truststore for server certificate verification.
</font>

改正之前代码

```yml
spring:
    datasource:
        driver-class-name: com.mysql.jdbc.Driver
        url: jdbc:mysql://127.0.0.1:3306/dbUser
        username: root
        password: 123456
```

改正之后代码，useSSL设置为true都可以

```yml
spring:
    datasource:
        driver-class-name: com.mysql.jdbc.Driver
        url: jdbc:mysql://127.0.0.1:3306/dbUser?useUnicode=true&characterEncoding=utf-8&useSSL=false
        username: root
        password: 123456
```

[源码地址 https://github.com/Q-Angelo/SpringBoot-Course/tree/master/chapter2/chapter2-1](https://github.com/Q-Angelo/SpringBoot-Course/tree/master/chapter2/chapter2-1)