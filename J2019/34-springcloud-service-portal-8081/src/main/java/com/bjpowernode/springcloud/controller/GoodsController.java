package com.bjpowernode.springcloud.controller;

import com.bjpowernode.springcloud.constants.Constant;
import com.bjpowernode.springcloud.model.ResultObject;
import com.bjpowernode.springcloud.service.GoodsRemoteClient;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RefreshScope
@RestController
public class GoodsController {

    //产品服务的接口地址（直连）
    private static final String GOODS_SERVICE_URL = "http://localhost:9100/service/goods";

    //产品服务的接口地址 （注册中心服务名）
    private static final String GOODS_SERVICE_URL_02 = "http://34-SPRINGCLOUD-SERVICE-GOODS/service/goods";

    //feign的远程调用客户端
    @Autowired
    private GoodsRemoteClient goodsRemoteClient;

    /**
     * 此类可以进行http接口的调用，除此之外还有apache httpClient, java.net.URL相关类也可以
     *
     */
    @Autowired
    private RestTemplate restTemplate;

    /**
     * 读取远程配置中心的配置信息
     */
    @Value("${eureka.client.service-url.defaultZone}")
    private String defaultZone;

    /**
     * 读取远程配置中心的配置信息
     */
    @Value("${info.address}")
    private String address;

    @Autowired
    private HttpHeaders httpHeaders;

    /**
     * 查询所有商品
     *
     * @return
     */
    @RequestMapping("/cloud/goods")
    public ResultObject goods() {
        System.out.println("/cloud/goods-->8080");
        //调用远程的一个controller, restful的调用
        ResponseEntity<ResultObject> responseEntity = restTemplate.getForEntity(GOODS_SERVICE_URL_02, ResultObject.class);

        return responseEntity.getBody();
    }

    @RequestMapping("/cloud/goods2")
    public ResultObject goods2() {
        System.out.println("/cloud/goods-->8080");
        //调用远程的一个controller, restful的调用
        ResponseEntity<ResultObject> responseEntity = restTemplate.exchange(GOODS_SERVICE_URL_02, HttpMethod.GET, new HttpEntity<Object>(httpHeaders), ResultObject.class);
        return responseEntity.getBody();
    }

    /**
     * 查询所有商品
     *
     * @return
     */
    @RequestMapping("/cloud/goodsFeign")
    public ResultObject goodsFeign() {
        System.out.println("/cloud/goods-->8080");
        //调用远程的一个controller, restful的调用，通过feign这种声明式的远程调用，goodsRemoteClient就像dubbo里面的接口层一样
        return goodsRemoteClient.goods();
    }


    /**
     * 查询所有商品
     *
     * @return
     */
    @HystrixCommand(fallbackMethod = "fallback",
            commandProperties={
                    @HystrixProperty(name="execution.timeout.enabled", value="true"),
                    @HystrixProperty(name="execution.isolation.thread.timeoutInMilliseconds", value="5000")
            }, ignoreExceptions = Throwable.class)
    @RequestMapping("/cloud/goodsHystrix")
    public ResultObject goodsHystrix() {
        System.out.println("/cloud/goodsHystrix-->8080");
        //1、服务超时，会降级
        /*try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }*/

        //2、服务异常，会降级
        /*String str = null;
        if (str == null) {
            throw new RuntimeException("服务异常了.");
        }*/

        //调用远程的一个controller, restful的调用，通过feign这种声明式的远程调用，goodsRemoteClient就像dubbo里面的接口层一样
        return goodsRemoteClient.goods();
    }

    /**
     * 降级方法 (备用的方法)
     *
     * @return
     */
    public ResultObject fallback(Throwable throwable) {
        throwable.printStackTrace();
        System.out.println(throwable.getMessage());
        return new ResultObject(Constant.ONE, "服务降级了.");
    }


    /**
     * 查询所有商品
     *
     * @return
     */
    @HystrixCommand(fallbackMethod = "fallback")
    @RequestMapping("/cloud/goodsRibbon")
    public ResultObject goodsRibbon() {
        System.out.println("/cloud/goodsRibbon-->8080");
        //调用远程的一个controller, restful的调用
        ResponseEntity<ResultObject> responseEntity = restTemplate.getForEntity(GOODS_SERVICE_URL_02, ResultObject.class);

        return responseEntity.getBody();
    }



    /**
     * 查询所有商品
     *
     * @return
     */
    @HystrixCommand(fallbackMethod = "fallback",
                    threadPoolKey = "goods",
                    threadPoolProperties = {
                        @HystrixProperty(name = "coreSize", value = "2"),
                        @HystrixProperty(name = "maxQueueSize", value = "1")
                    })
    @RequestMapping("/cloud/goodsLimit")
    public ResultObject goodsLimit() {
        System.out.println("/cloud/goodsLimit-->8080");
        //调用远程的一个controller, restful的调用
        ResponseEntity<ResultObject> responseEntity = restTemplate.getForEntity(GOODS_SERVICE_URL_02, ResultObject.class);

        return responseEntity.getBody();
    }



    /**
     * 查询所有商品
     *
     * @return
     */
    @RequestMapping("/cloud/goodsFeignHystrix")
    public ResultObject goodsFeignHystrix() {
        //调用远程的一个controller, restful的调用，通过feign这种声明式的远程调用，goodsRemoteClient就像dubbo里面的接口层一样
        return goodsRemoteClient.goods();
    }

    /**
     * 读取远程配置中心的配置
     *
     * @return
     */
    @RequestMapping("/cloud/getConfig")
    public Object getConfig() {
        return defaultZone + " --- " + address;
    }
}