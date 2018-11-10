package com.angelo.aspect;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;

@Aspect
@Component
public class HttpAspect {
    private final static Logger logger = LoggerFactory.getLogger(HttpAspect.class);

    /**
     *  定义一个公共的方法，实现服用
     *  拦截UserController下面的所有方法
     *  拦截UserController下面的userList方法里的任何参数(..表示拦截任何参数)写法：@Before("execution(public * com.angelo.controller.UserController.userList(..))")
     */
    @Pointcut("execution(public * com.angelo.controller.UserController.*(..))")
    public void log() {
    }

    @Before("log()")
    public void doBefore(JoinPoint joinPoint) {
        ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        HttpServletRequest request = attributes.getRequest();

        Map params = new HashMap();
        params.put("url", request.getRequestURL()); // 获取请求的url
        params.put("method", request.getMethod()); // 获取请求的方式
        params.put("ip", request.getRemoteAddr()); // 获取请求的ip地址
        params.put("className", joinPoint.getSignature().getDeclaringTypeName()); // 获取类名
        params.put("classMethod", joinPoint.getSignature().getName()); // 获取类方法
        params.put("args", joinPoint.getArgs()); // 请求参数

        // 输出格式化后的json字符串
        Gson gson = new GsonBuilder().setPrettyPrinting().create();

        logger.info("REQUEST: {}", gson.toJson(params));
    }

    @After("log()")
    public void doAfter() {
        logger.info("doAfter");
    }

    /**
     * 获取响应返回值
     * @param object
     */
    @AfterReturning(returning = "object", pointcut = "log()")
    public void doAfterReturning(Object object) {
        logger.info("RESPONSE: {}", object); // 会打印出一个对象，想打印出具体内容需要在定义模型处加上toString()

        logger.info("RESPONSE: {}", object.toString());
    }
}
