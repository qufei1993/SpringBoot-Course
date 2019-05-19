package com.example.consul;

import com.example.consul.config.StudentConfig;
import com.example.consul.config.TeachConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@EnableDiscoveryClient
@RestController
@SpringBootApplication
@EnableConfigurationProperties({ StudentConfig.class, TeachConfig.class })
public class ConsulApplication {

    @Value("${description}")
    private String description;

    @Autowired
    private StudentConfig studentConfig;

    @Autowired
    private TeachConfig teachConfig;

    @GetMapping("/health")
    public String Health() {
        System.out.println("health");
        return "OK";
    }

    @GetMapping("/user/description")
    public String Description() {
        return description;
    }

    @GetMapping("/user/student/intro")
    public String StudentIntro() {
        return studentConfig.toString();
    }

    @GetMapping("/user/teach/intro")
    public String TeachIntro() {
        return teachConfig.toString();
    }

    public static void main(String[] args) {
        SpringApplication.run(ConsulApplication.class, args);
    }
}
