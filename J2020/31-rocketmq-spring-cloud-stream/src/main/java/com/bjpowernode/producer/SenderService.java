package com.bjpowernode.producer;

import org.apache.rocketmq.common.message.MessageConst;
import org.apache.rocketmq.spring.core.RocketMQTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.stream.messaging.Source;
import org.springframework.messaging.Message;
import org.springframework.messaging.MessageHeaders;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.stereotype.Service;
import org.springframework.util.MimeTypeUtils;

@Service
public class SenderService {

    //spring cloud stream里面发消息通过 Source 发送
    @Autowired
    private Source source;

    @Autowired
    private MySource mySource;

    //原来springboot里面通过 RocketMQTemplate 发送

    @Autowired
    private RocketMQTemplate rocketMQTemplate;

    /**
     * 发送消息的方法
     *
     * @param msg
     * @throws Exception
     */
    public void send(String msg) throws Exception {
        // source.output() == MessageChannel 消息通道
        boolean flag = source.output().send(MessageBuilder.withPayload(msg).build());
        System.out.println("消息发送：" + flag);
    }

    /**
     * 发送消息的方法
     *
     * @param msg
     * @throws Exception
     */
    public void send1(String msg) throws Exception {
        // source.output() == MessageChannel 消息通道
        boolean flag = mySource.output1().send(MessageBuilder.withPayload(msg).build());
        System.out.println("消息发送1：" + flag);
    }

    /**
     * 发送消息的方法,发到3个topic中
     *
     * @param msg
     * @throws Exception
     */
    public void multiSend(String msg) throws Exception {
        // source.output() == MessageChannel 消息通道
        boolean flag = source.output().send(MessageBuilder.withPayload(msg).build());
        System.out.println("消息发送：" + flag);

        // source.output() == MessageChannel 消息通道
        boolean flag1 = mySource.output1().send(MessageBuilder.withPayload(msg).build());
        System.out.println("消息发送1：" + flag1);

        // source.output() == MessageChannel 消息通道
        boolean flag2 = mySource.output2().send(MessageBuilder.withPayload(msg).build());
        System.out.println("消息发送2：" + flag2);
    }


    public <T> void sendObject(T msg, String tag) throws Exception {
        Message message = MessageBuilder.withPayload(msg)
                .setHeader(MessageConst.PROPERTY_TAGS, tag)
                .setHeader(MessageHeaders.CONTENT_TYPE, MimeTypeUtils.APPLICATION_JSON)
                .build();
        boolean flag2 = mySource.output1().send(message);

        System.out.println("对象消息发送2：" + flag2);
    }

    /**
     * 发送消息的方法
     *
     * @param msg
     * @throws Exception
     */
    public void sendTemplate(String msg) throws Exception {
        Message message = MessageBuilder.withPayload(msg)
                .setHeader(MessageHeaders.CONTENT_TYPE, MimeTypeUtils.APPLICATION_JSON)
                .build();
       rocketMQTemplate.send("test-topic1", message);
        System.out.println("发送完毕......");
    }
}
