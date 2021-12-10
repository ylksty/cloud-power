package com.bjpowernode.consumer;

import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.annotation.StreamListener;

import java.util.Date;

@EnableBinding(value = {/*Sink.class,*/ MySink.class})
public class ReceiveService {

    /*@StreamListener("input")
    public void receiveInput(String receiveMsg) {
        System.out.println("input1 接收到的消息: " + receiveMsg);
    }*/

    @StreamListener("input1")
    public void receiveInput1(String receiveMsg) {
        System.out.println(new Date() + " -- input1 接收到的消息: " + receiveMsg);
    }

    /*@StreamListener("input1")
    public void receiveInput1(@Payload Orders orders) {
        System.out.println("input1 接收到的消息: " + orders);
    }*/

    //@StreamListener("input4")
    public void receiveTransactionalMsg(String transactionMsg) {
        System.out.println("input4 接收到的transaction消息: " + transactionMsg);
    }
}