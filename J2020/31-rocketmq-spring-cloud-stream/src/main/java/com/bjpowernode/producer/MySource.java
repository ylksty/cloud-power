package com.bjpowernode.producer;

import org.springframework.cloud.stream.annotation.Output;
import org.springframework.messaging.MessageChannel;

public interface MySource {

    /**
     * Name of the output channel.
     */
    String OUTPUT1 = "output1";

    /**
     * Name of the output channel.
     */
    String OUTPUT2 = "output2";

    /**
     * Name of the output channel.
     */
    String OUTPUTTX = "outputTX";

    /**
     * @return output channel
     */
    @Output(MySource.OUTPUT1)
    MessageChannel output1();

    @Output(MySource.OUTPUT2)
    MessageChannel output2();

    @Output(MySource.OUTPUTTX)
    MessageChannel outputTX();
}
