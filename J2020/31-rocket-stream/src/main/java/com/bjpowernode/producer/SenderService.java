package com.bjpowernode.producer;

import org.apache.rocketmq.common.message.MessageConst;
import org.apache.rocketmq.spring.support.RocketMQHeaders;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.messaging.Source;
import org.springframework.messaging.Message;
import org.springframework.messaging.MessageHeaders;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.stereotype.Service;
import org.springframework.util.MimeTypeUtils;

import java.util.Date;

@EnableBinding(value = {Source.class, MySource.class})
@Service
public class SenderService {

    @Autowired
    private Source source;

    @Autowired
    private MySource mySource;

    public void send(String msg) throws Exception {
        boolean flag = source.output().send(MessageBuilder.withPayload(msg).build());
        System.out.println("input 消息发送：" + flag);
    }

    public void send1(String msg) throws Exception {
        boolean flag = mySource.output1().send(MessageBuilder.withPayload(msg).build());
        System.out.println("input1 消息发送：" + flag);
    }

    public void send2(String msg) throws Exception {
        boolean flag = mySource.output2().send(MessageBuilder.withPayload(msg).build());
        System.out.println("input2 消息发送：" + flag);
    }

    public void send3(String msg) throws Exception {
        boolean flag = mySource.output3().send(MessageBuilder.withPayload(msg).build());
        System.out.println("input3 消息发送：" + flag);
    }

    public <T> void sendObject(T msg) throws Exception {
        Message message = MessageBuilder.withPayload(msg)
                .setHeader(MessageHeaders.CONTENT_TYPE, MimeTypeUtils.APPLICATION_JSON)
                .build();
        boolean flag = mySource.output1().send(message);
        System.out.println("input3 消息发送：" + flag);
    }

    public <T> void sendObject(T msg, String tag) throws Exception {
        Message message = MessageBuilder.withPayload(msg)
                .setHeader(MessageConst.PROPERTY_TAGS, tag)
                .setHeader(MessageHeaders.CONTENT_TYPE, MimeTypeUtils.APPLICATION_JSON)
                .setHeader(MessageConst.PROPERTY_DELAY_TIME_LEVEL, 1)
                .build();
        boolean flag = mySource.output1().send(message);
        System.out.println(new Date() + " -- input1 消息发送：" + flag);
    }

    public <T> void sendTransactionalMsg(T msg, int num) throws Exception {
        MessageBuilder builder = MessageBuilder.withPayload(msg)
                .setHeader(MessageHeaders.CONTENT_TYPE, MimeTypeUtils.APPLICATION_JSON);
        builder.setHeader("test", String.valueOf(num));
        builder.setHeader(RocketMQHeaders.TAGS, "binder");
        Message message = builder.build();
        boolean flag = mySource.output4().send(message);
        System.out.println("input4 事务消息发送：" + flag);
    }
}