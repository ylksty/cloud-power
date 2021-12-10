package com.bjpowernode.springcloud.message;

import org.springframework.cloud.stream.annotation.Output;
import org.springframework.messaging.MessageChannel;

public interface MessageSource {

    /**
     * Name of the output channel.
     */
    String OUTPUT = "myOutput";

    /**
     * @return output channel
     */
    @Output(MessageSource.OUTPUT)
    MessageChannel output();
}
