package com.bjpowernode.springcloud.service;

import com.bjpowernode.springcloud.message.MessageSender;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MessageService {

    @Autowired
    private MessageSender messageSender;

    public void sendMessage(String msg) {
        System.out.println("--------业务处理 省略-------");
        messageSender.publish(msg);
    }
}
