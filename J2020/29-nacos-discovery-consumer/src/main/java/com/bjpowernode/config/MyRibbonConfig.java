package com.bjpowernode.config;

import com.alibaba.cloud.nacos.ribbon.NacosRule;
import com.alibaba.cloud.sentinel.annotation.SentinelRestTemplate;
import com.bjpowernode.sentinel.MyBlockHandlerClass;
import com.netflix.loadbalancer.IPing;
import com.netflix.loadbalancer.IRule;
import com.netflix.loadbalancer.PingUrl;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

@Configuration
public class MyRibbonConfig {

    @SentinelRestTemplate(/*blockHandler="blockA", blockHandlerClass= MyBlockHandlerClass.class*/ //限流
            fallback="fallbackA", fallbackClass = MyBlockHandlerClass.class) // 降级
    @LoadBalanced //负载均衡
    @Bean
    public RestTemplate restTemplate() {
        return new RestTemplate();
    }

    /**
     * 更改负载均衡策略，默认是ZoneAvoidanceRule策略
     *
     * @return
     */
    @Bean
    public IRule iRule() {
        return new NacosRule();
    }

    @Bean
    public IPing iPing () {
        return new PingUrl();
    }
}