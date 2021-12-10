package com.bjpowernode.springcloud.config;

import feign.auth.BasicAuthRequestInterceptor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class FeignConfiguration {

    //@RequestLine("GET /service/goods")

    /**
     * 一种契约，采用feign的契约方式，如果不配置该bean，会转成SpringMVC的方式
     */
    /*@Bean
    public Contract feignContract(){
        return new Contract.Default();
    }*/

    @Bean
    public BasicAuthRequestInterceptor basicAuthRequestInterceptor(){
        //传用户名和密码
        return new BasicAuthRequestInterceptor("cat","123456");
    }
}