package com.bjpowernode.springcloud.message;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.messaging.support.MessageBuilder;

import java.util.Date;

//将信道channel和exchange绑定在一起
@EnableBinding(MessageSource.class)
public class MessageSender {

    @Autowired
    private MessageSource messageSource; // 消息的发送信道

    public void publish(String msg) {
        //通过信道发消息
        boolean  flag = messageSource.output().send(MessageBuilder.withPayload(msg).build());
        System.out.println("消息发送：<" + msg + "> 完成，时间：" + new Date() + ", flag=" + flag);
    }
}
