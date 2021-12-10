package com.bjpowernode.springcloud.service;

import com.bjpowernode.springcloud.constants.Constant;
import com.bjpowernode.springcloud.model.Orders;
import com.bjpowernode.springcloud.model.ResultObject;
import org.springframework.stereotype.Component;

@Component
public class OrdersRemoteClientFallBack implements OrdersRemoteClient {

    @Override
    public ResultObject order(Integer uid, Integer goodsId, Integer buyNum, Orders orders) {
        return new ResultObject(Constant.ONE,"feign服务调用降级order()方法");
    }

    @Override
    public ResultObject orders() {
        return new ResultObject(Constant.ONE,"feign服务调用降级orders()方法");
    }
}
