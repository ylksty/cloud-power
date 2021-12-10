package com.bjpowernode;

import com.bjpowernode.producer.SenderService;
import org.apache.rocketmq.client.producer.DefaultMQProducer;
import org.apache.rocketmq.spring.core.RocketMQTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.integration.channel.DirectChannel;
import org.springframework.messaging.*;
import org.springframework.messaging.support.MessageBuilder;

@SpringBootApplication
public class Application implements CommandLineRunner {

    @Autowired
    private SenderService senderService;

    @Autowired
    private RocketMQTemplate rocketMQTemplate;

    @Autowired
    private DefaultMQProducer defaultMQProducer;

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }

    public void run(String... args) throws Exception {
        //senderService.send("send Hello, Spring Cloud Stream RocketMQ.");
        //senderService.send1("send1 Hello, Spring Cloud Stream RocketMQ.");
        //senderService.send2("send2 Hello, Spring Cloud Stream RocketMQ.");
        //senderService.send3("send3 Hello, Spring Cloud Stream RocketMQ.");
        senderService.sendObject("delay Hello, Spring Cloud Stream RocketMQ.","tag");

        //发送对象消息
        //senderService.sendObject(Orders.builder().userId(1).productId(1256).build());

        // COMMIT_MESSAGE message
        //senderService.sendTransactionalMsg("transactional-msg1", 1);
        // ROLLBACK_MESSAGE message
        //senderService.sendTransactionalMsg("transactional-msg2", 2);
        // ROLLBACK_MESSAGE message
        //senderService.sendTransactionalMsg("transactional-msg3", 3);
        // COMMIT_MESSAGE message
        //senderService.sendTransactionalMsg("transactional-msg4", 4);

        rocketMQTemplate.send("test-topic", MessageBuilder.withPayload("test test....").build());

        MessageChannel messageChannel = new DirectChannel();
        // 消息订阅
        ((SubscribableChannel) messageChannel).subscribe(new MessageHandler() {
            @Override
            public void handleMessage(Message<?> message) throws MessagingException {
                System.out.println("receive msg: " + message.getPayload());
            }
        });
        // 消息发送
        boolean flag = messageChannel.send(MessageBuilder.withPayload("simple msg").build());
        System.out.println("send msg: " + flag);
    }
}