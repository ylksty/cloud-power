package com.bjpowernode.springcloud;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

//@EnableApolloConfig //开启apollo配置支持
@EnableEurekaClient
@SpringBootApplication
public class OrdersApplication8100 {

    public static void main(String[] args) {
        SpringApplication.run(OrdersApplication8100.class, args);
    }
}