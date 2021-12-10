package com.bjpowernode.transaction;

import com.bjpowernode.producer.MySource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.Message;
import org.springframework.messaging.MessageHeaders;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.stereotype.Component;
import org.springframework.util.MimeTypeUtils;

@Component
public class Sender {

    @Autowired
    private MySource mySource;

    public <T> void sendTransactionalMsg(T msg, int num) throws Exception {
        MessageBuilder builder = MessageBuilder.withPayload(msg)
                .setHeader(MessageHeaders.CONTENT_TYPE, MimeTypeUtils.APPLICATION_JSON)
                .setHeader("test", String.valueOf(num));
                //.setHeader(RocketMQHeaders.TAGS, "binder");
        Message message = builder.build();

        mySource.outputTX().send(message);
    }
}
