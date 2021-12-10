package com.bjpowernode.consumer;

import org.springframework.cloud.stream.annotation.Input;
import org.springframework.messaging.SubscribableChannel;

public interface MySink {

    @Input("input1")
    SubscribableChannel input1();

    //@Input("input4")
    SubscribableChannel input4();

    //@Input("input5")
    //PollableMessageSource input5();
}