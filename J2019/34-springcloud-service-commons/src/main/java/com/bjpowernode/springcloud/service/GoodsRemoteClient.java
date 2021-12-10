package com.bjpowernode.springcloud.service;

import com.bjpowernode.springcloud.config.FeignConfiguration;
import com.bjpowernode.springcloud.model.ResultObject;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.*;

@Component
@FeignClient(value="34-SPRINGCLOUD-SERVICE-GOODS",
             fallback=GoodsRemoteClientFallBack.class,
             /*fallbackFactory = GoodsRemoteClientFallBackFactory.class,*/
             configuration = FeignConfiguration.class)
public interface GoodsRemoteClient {

    /**
     * 声明一个feign的接口，它的实现是服务提供者的controller实现
     *
     * @return
     */
    @RequestMapping(value = "/service/goods", method = RequestMethod.GET)
    public ResultObject goods();

    /**
     * 声明一个feign的接口，它的实现是服务提供者的controller实现
     *
     * @return
     */
    @RequestMapping(value = "/service/goods/{id}", method = RequestMethod.GET)
    public ResultObject goodsDetail(@PathVariable("id") Integer id);

    /**
     * 减库存
     *
     * @param id
     * @param buyNum
     * @return
     */
    @RequestMapping(value = "/service/goods/{id}", method = RequestMethod.POST)
    public ResultObject decrByStore (@PathVariable("id") Integer id, @RequestParam("buyNum") Integer buyNum);
}