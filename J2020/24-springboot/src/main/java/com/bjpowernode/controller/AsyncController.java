package com.bjpowernode.controller;

import com.bjpowernode.service.AsyncService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutionException;

@RestController
public class AsyncController {

    private static final Logger logger = LoggerFactory.getLogger(AsyncController.class);

    @Autowired
    private AsyncService asyncExecutorService;

    @RequestMapping("/async")
    public Object async() throws ExecutionException, InterruptedException {
        System.out.println("start submit");

        //调用service层的异步任务
        //(发短信，要发10几万短信，为了提高发短信的效率，所以可以把发短信用线程池执行，同时可以有多个线程在执行发短信)
        //Future<String> future = asyncExecutorService.sendSMS();
        //Object result = asyncExecutorService.sendSMS();

        CountDownLatch countDownLatch = new CountDownLatch(3);

        List<List<String>> lists = new ArrayList<>();

        List<String> list1 = new ArrayList<>();
        list1.add("1-01");
        list1.add("1-02");
        list1.add("1-03");

        List<String> list2 = new ArrayList<>();
        list2.add("2-01");
        list2.add("2-02");
        list2.add("2-03");

        List<String> list3 = new ArrayList<>();
        list3.add("3-01");
        list3.add("3-02");
        list3.add("3-03");

        lists.add(list1);
        lists.add(list2);
        lists.add(list3);

        //线程数为分批插入数据数
        for (int i = 0; i < lists.size(); i++) {
            List<String> insert = lists.get(i);
            System.out.println("插入数据" + (i + 1) + "正在更新HF数据库,共" + lists.size() + "个片段, 每片" + insert.size() +"条数据");
            asyncExecutorService.sendSMS(insert, countDownLatch);
        }
        countDownLatch.await();

        System.out.println("end submit");

        return "OK";
    }
}