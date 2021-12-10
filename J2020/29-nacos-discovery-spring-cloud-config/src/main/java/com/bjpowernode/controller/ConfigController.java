package com.bjpowernode.controller;

import com.bjpowernode.property.MyProperties;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ConfigController {

    @Value("${user.name:}")
    private String name;

    @Value("${user.age:}")
    private String age;

    @Autowired
    private MyProperties myProperties;

    @RequestMapping("/config")
    public String config() {
        return name + "--" + age;
    }

    @RequestMapping("/config2")
    public String config2() {
        return myProperties.getName() + "--" + myProperties.getAge();
    }
}