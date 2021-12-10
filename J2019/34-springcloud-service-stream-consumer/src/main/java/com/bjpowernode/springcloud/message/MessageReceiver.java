package com.bjpowernode.springcloud.message;

import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.messaging.Message;

import java.util.Date;

@EnableBinding(MessageSink.class)
public class MessageReceiver {

    @StreamListener(MessageSink.INPUT)
    public void input(Message message) {
        System.out.println("消息接收：<" + message.getPayload()  + "> 完成，时间：" + new Date());
    }
}
