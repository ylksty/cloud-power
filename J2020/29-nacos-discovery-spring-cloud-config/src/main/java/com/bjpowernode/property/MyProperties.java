package com.bjpowernode.property;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Data //lombok
@Component
@ConfigurationProperties(prefix = "user")
public class MyProperties {

    private String name;

    private int age;

}