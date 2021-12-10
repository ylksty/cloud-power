package com.bjpowernode.runner;

import com.alibaba.cloud.nacos.NacosConfigManager;
import com.alibaba.nacos.api.config.listener.Listener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.io.StringReader;
import java.util.Properties;
import java.util.concurrent.Executor;

@Component
public class SampleRunner implements ApplicationRunner {

    @Value("${user.name}")
    String name;

    @Value("${user.age}")
    int age;

    @Autowired
    private NacosConfigManager nacosConfigManager;

    @Override
    public void run(ApplicationArguments args) throws Exception {
        System.out.println(
                String.format("Initial address=%s, phone=%s", name, age));

        nacosConfigManager.getConfigService().addListener(
                "29-nacos-discovery-spring-cloud-config.properties", "DEFAULT_GROUP", new Listener() {

                    /**
                     * Callback with latest config data.
                     *
                     * For example, config data in Nacos is:
                     *
                     * user.name=Nacos
                     * user.age=25
                     * @param configInfo latest config data for specific dataId in Nacos
                     * server
                     */
                    @Override
                    public void receiveConfigInfo(String configInfo) {
                        Properties properties = new Properties();
                        try {
                            properties.load(new StringReader(configInfo));
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                        System.out.println("config changed: " + properties);
                    }

                    @Override
                    public Executor getExecutor() {
                        return null;
                    }
                });
    }
}