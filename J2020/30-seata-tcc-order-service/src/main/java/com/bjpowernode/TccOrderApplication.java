package com.bjpowernode;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;

/**
 * TCC事务模式 案例
 * 该项目为 TM 发起事务方
 */
@EnableDiscoveryClient
@EnableFeignClients
@SpringBootApplication
public class TccOrderApplication {

    public static void main(String[] args) {
        SpringApplication.run(TccOrderApplication.class, args);
    }
}