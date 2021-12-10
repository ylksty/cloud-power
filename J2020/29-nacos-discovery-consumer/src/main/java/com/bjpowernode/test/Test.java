package com.bjpowernode.test;

import org.springframework.web.client.RestTemplate;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Test {

    public static void main(String[] args)  {
        ExecutorService executorService = Executors.newFixedThreadPool(16);
        RestTemplate restTemplate = new RestTemplate();
        while (true) {
            try {
                executorService.submit(() -> {
                    String ss = restTemplate.getForObject("http://localhost:8083/app2", String.class);
                    System.out.println(ss);
                });
                Thread.sleep(100);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}