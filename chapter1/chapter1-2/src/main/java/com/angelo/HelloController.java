package com.angelo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMethod;

@RestController
public class HelloController {

    @Autowired
    private UserProperties userProperties;

    @RequestMapping(value = "/hello", method = RequestMethod.GET)
    public String say() {

        return "我是 " + userProperties.getNickName() + userProperties.getInfo();
    }
}
