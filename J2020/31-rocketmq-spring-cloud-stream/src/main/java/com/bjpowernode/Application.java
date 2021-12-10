package com.bjpowernode;

import com.bjpowernode.consumer.MySink;
import com.bjpowernode.consumer.ReceiveService;
import com.bjpowernode.producer.MySource;
import com.bjpowernode.producer.SenderService;
import com.bjpowernode.transaction.Sender;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.messaging.Sink;
import org.springframework.cloud.stream.messaging.Source;

@EnableBinding(value = {Source.class, Sink.class, MySource.class, MySink.class}) //使其生效
@SpringBootApplication
public class Application implements CommandLineRunner {

    @Autowired
    private SenderService senderService;

    @Autowired
    private ReceiveService receiveService;

    @Autowired
    private Sender sender;

    /*@Bean
    public ObjectMapper objectMapper() {
        return new ObjectMapper();
    }*/

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        //senderService.send("Hello spring cloud stream rocketmq.");

        senderService.send1("Hello spring cloud stream rocketmq 1.");

        //senderService.multiSend("Hello spring cloud stream rocketmq multi....");

        //senderService.sendObject(Orders.builder().buynum(1).goodsid(1908).id(102).build(), "myTag");
        //receiveService.receive1();

        //senderService.sendTemplate("template meaasge....");

        //sender.sendTransactionalMsg("transaction message", 1);
        //sender.sendTransactionalMsg("transaction message", 2);
        //sender.sendTransactionalMsg("transaction message", 3);
        //sender.sendTransactionalMsg("transaction message", 4);
    }
}