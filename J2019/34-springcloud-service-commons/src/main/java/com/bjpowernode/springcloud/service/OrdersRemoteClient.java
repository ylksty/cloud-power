package com.bjpowernode.springcloud.service;

import com.bjpowernode.springcloud.model.Orders;
import com.bjpowernode.springcloud.config.FeignConfiguration;
import com.bjpowernode.springcloud.model.ResultObject;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@Component
@FeignClient(value="34-SPRINGCLOUD-SERVICE-ORDERS",
             fallback=OrdersRemoteClientFallBack.class,
             /*fallbackFactory = OrdersRemoteClientFallBackFactory.class,*/
             configuration = FeignConfiguration.class)
public interface OrdersRemoteClient {

    /**
     * 声明一个feign的接口，它的实现是服务提供者的controller实现
     *
     * @return
     */
    @RequestMapping(value = "/service/order", method = RequestMethod.POST)
    public ResultObject order(@RequestParam("uid") Integer uid,
                              @RequestParam("goodsId") Integer goodsId,
                              @RequestParam("buyNum") Integer buyNum,
                              @RequestBody Orders orders);


    /**
     * 声明一个feign的接口，它的实现是服务提供者的controller实现
     *
     * @return
     */
    @RequestMapping(value = "/service/orders", method = RequestMethod.GET)
    public ResultObject orders();
}
