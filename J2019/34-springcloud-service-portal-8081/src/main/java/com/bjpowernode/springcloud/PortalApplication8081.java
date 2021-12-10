package com.bjpowernode.springcloud;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.netflix.hystrix.EnableHystrix;
import org.springframework.cloud.openfeign.EnableFeignClients;

@EnableHystrix  //开启Hystrix的服务熔断降级支持
@EnableFeignClients //开启feign的客户端调用支持
@EnableEurekaClient //开启Eureka的客户端调用支持
@SpringBootApplication
/**
 * @SpringBootApplication
 * @EnableDiscoveryClient
 * @EnableCircuitBreaker
 */
//@SpringCloudApplication
public class PortalApplication8081 {

    public static void main(String[] args) {
        SpringApplication.run(PortalApplication8081.class, args);
    }
}