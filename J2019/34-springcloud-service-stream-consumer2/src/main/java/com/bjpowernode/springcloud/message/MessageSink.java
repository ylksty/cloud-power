package com.bjpowernode.springcloud.message;

import org.springframework.cloud.stream.annotation.Input;
import org.springframework.messaging.SubscribableChannel;

public interface MessageSink {

    /**
     * Input channel name.
     */
    String INPUT = "myInput";

    /**
     * @return input channel.
     */
    @Input(MessageSink.INPUT)
    SubscribableChannel input();
}
