package com.bjpowernode;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.context.ConfigurableApplicationContext;

import java.util.concurrent.TimeUnit;

@EnableDiscoveryClient
@SpringBootApplication
public class Application {

    public static void main(String[] args) throws InterruptedException {
        ConfigurableApplicationContext applicationContext = SpringApplication.run(Application.class, args);

        while(true) {
            //当动态配置刷新时，会更新到 Enviroment中，因此此处每隔一秒从Enviroment中获取配置
            String userName = applicationContext.getEnvironment().getProperty("user.name");
            String userAge = applicationContext.getEnvironment().getProperty("user.age");
            System.out.println("user name : " + userName + "; age: " + userAge);

            //获取当前部署的环境
            String currentEnv = applicationContext.getEnvironment().getProperty("current.env");
            System.err.println("in [ "+currentEnv+" ] enviroment; "+"user name :" + userName + "; age: " + userAge);

            TimeUnit.SECONDS.sleep(1);
        }
    }
}