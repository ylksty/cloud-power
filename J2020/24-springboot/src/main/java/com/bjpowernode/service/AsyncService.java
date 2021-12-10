package com.bjpowernode.service;

import java.util.List;
import java.util.concurrent.CountDownLatch;

public interface AsyncService {

    //public Future<String> sendSMS ();

    //public Object sendSMS();

    public void sendSMS(List<String> list, CountDownLatch countDownLatch);
}
