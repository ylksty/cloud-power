package com.bjpowernode.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class NameServerController {

    @RequestMapping("/rocketmq/msaddr")
    public String msaddr() {
        return "192.168.172.127:9876";
    }
}