package com.bjpowernode.springcloud.config;

import java.util.*;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.atomic.AtomicInteger;

public class Test {

    public static void main(String[] args) {
            List<Map<String,Object>> paramList = Collections.synchronizedList(new ArrayList<>());
            AtomicInteger atomicInteger = new AtomicInteger(1);

            ExecutorService executorService = Executors.newFixedThreadPool(8);
            //创建一个倒计数器，计数是1000，也就是你的任务个数
            CountDownLatch latch = new CountDownLatch(1000);
            for(int i =0;i<1000;i++){
                executorService.submit(new Runnable() {
                    @Override
                    public void run() {
                        Map<String,Object> map = new HashMap<>();
                        map.put("likeId", UUID.randomUUID().toString());
                        map.put("personLike",atomicInteger.getAndIncrement());
                        map.put("status",0);
                        paramList.add(map);
                        //每完成一个任务，计数器减1
                        latch.countDown();
                    }
                });
            }

        try {
            //等1000个任务都执行完
            latch.await();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        //现在再入库
        System.out.println(paramList.size());
    }
}
