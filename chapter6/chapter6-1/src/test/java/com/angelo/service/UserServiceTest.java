package com.angelo.service;

import com.angelo.aspect.HttpAspect;
import com.angelo.domain.User;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;


@RunWith(SpringRunner.class)
@SpringBootTest // 将启动整个Spring Boot工程
public class UserServiceTest {

    @Autowired
    private UserService userService;

    private final static Logger logger = LoggerFactory.getLogger(HttpAspect.class);

    @Test
    public void findByIdTest() {
        User user = userService.findById(1);

        Assert.assertEquals("张三", user.getName());
        Assert.assertEquals(new Integer(-1), user.getAge());
    }
}