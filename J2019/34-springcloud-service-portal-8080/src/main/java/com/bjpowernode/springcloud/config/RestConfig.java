package com.bjpowernode.springcloud.config;

import com.netflix.loadbalancer.IRule;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpHeaders;
import org.springframework.web.client.RestTemplate;

import java.nio.charset.Charset;
import java.util.Base64;

@Configuration
public class RestConfig {

    @LoadBalanced  //使用Ribbon实现负载均衡的调用 (spring cloud -> 封装ribbon + eureka + restTemplate)
    @Bean
    public RestTemplate restTemplate () {
        return new RestTemplate();
    }

    /**
     * 如果要切换负载均衡策略，早spring容器中配置一个负载均衡策略的bean即可
     *
     * @return
     */
    //@Bean
    public IRule iRule() {
        //采用轮询方式负载均衡
        return new MyRule();
    }

    /**
     * 进行Http头信息配置实现安全认证
     *
     * @return
     */
    @Bean
    public HttpHeaders getHeaders() {
        //定义HTTP的头信息
        HttpHeaders headers = new HttpHeaders();
        //认证的原始信息
        String auth = "cat:123456";
        //进行加密处理
        byte[] encodedAuth = Base64.getEncoder().encode(auth.getBytes(Charset.forName("US-ASCII")));
        String authHeader = "Basic " + new String(encodedAuth);
        headers.set("Authorization", authHeader);
        return headers;
    }
}