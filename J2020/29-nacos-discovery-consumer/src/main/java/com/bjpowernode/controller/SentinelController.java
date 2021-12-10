package com.bjpowernode.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
public class SentinelController {

    @Autowired
    private RestTemplate restTemplate;

    @GetMapping("/app1")
    public String app1() {
        try {
            Thread.sleep(2005);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println(111);
        return restTemplate.getForObject("http://29-nacos-discovery-provider/test", String.class);
    }

    @GetMapping("/app2")
    public String app2() {
        return restTemplate.getForObject("http://29-nacos-discovery-provider/test", String.class);
    }
}