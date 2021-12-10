package com.bjpowernode.springcloud;

import com.bjpowernode.springcloud.service.MessageService;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

@SpringBootApplication
public class StreamApplication {

    public static void main(String[] args) {
        ApplicationContext context = SpringApplication.run(StreamApplication.class, args);

        MessageService messageService = context.getBean(MessageService.class);

        messageService.sendMessage("Hello Spring Cloud Stream.");
    }
}