package com.bjpowernode.consumer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.cloud.stream.messaging.Sink;
import org.springframework.messaging.Message;
import org.springframework.stereotype.Service;

@Service
public class ReceiveService {

    //spring cloud stream里面发消息通过 Sink 发送
    @Autowired
    private Sink sink;

    @Autowired
    private MySink mySink;

    //原来springboot里面通过 RocketMQTemplate 发送

    public void receive() {
        // SubscribableChannel = sink.input() 消息订阅的信道
        sink.input().subscribe((Message<?> message) -> {
            System.out.println(message.getPayload());
        });
    }

    public void receive1() {
        // SubscribableChannel = sink.input() 消息订阅的信道
        mySink.input1().subscribe((Message<?> message) -> {
            System.out.println("input 1---" + message.getPayload());
        });
    }

    @StreamListener(value = Sink.INPUT)
    public void receiveMessage(String message) {
        System.out.println("接收到的消息是：" + message);
    }

    @StreamListener(value = MySink.INPUT1)
    public void receiveMessage1(String message) {
        System.out.println("接收到的消息是1：" + message);
    }

    @StreamListener(value = MySink.INPUT2)
    public void receiveMessage2(String message) {
        System.out.println("接收到的消息是2：" + message);
    }

    @StreamListener(value = MySink.INPUTTX)
    public void receiveTransactionMessage(String message) {
        System.out.println("接收到的 事务 消息是：" + message);
    }
}