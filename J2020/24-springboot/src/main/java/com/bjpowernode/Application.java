package com.bjpowernode;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class Application {

    @Value("${user.name:zhangsan}")
    private String name;

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }
}