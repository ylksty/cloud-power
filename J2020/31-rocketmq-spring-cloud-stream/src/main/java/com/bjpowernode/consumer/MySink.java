package com.bjpowernode.consumer;

import org.springframework.cloud.stream.annotation.Input;
import org.springframework.messaging.SubscribableChannel;

public interface MySink {

    /**
     * Input channel name.
     */
    String INPUT1 = "input1";

    /**
     * Input channel name.
     */
    String INPUT2 = "input2";

    /**
     * Input channel name.
     */
    String INPUTTX = "inputTX";

    /**
     * @return input channel.
     */
    @Input(MySink.INPUT1)
    SubscribableChannel input1();

    /**
     * @return input channel.
     */
    @Input(MySink.INPUT2)
    SubscribableChannel input2();

    /**
     * @return input channel.
     */
    @Input(MySink.INPUTTX)
    SubscribableChannel inputTX();
}