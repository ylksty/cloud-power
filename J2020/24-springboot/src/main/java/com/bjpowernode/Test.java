package com.bjpowernode;

import com.bjpowernode.config.MyConfig;

public class Test {

    public static void main(String[] args) {

        System.out.println(MyConfig.class.getClassLoader().getResource("cn/xx/my.conf").getPath());

        System.out.println(MyConfig.class.getClassLoader().getResource("mysource/changsha.jpg").getPath());

        System.out.println(Application.class.getClassLoader().getResource("server.jks").getPath());
    }
}