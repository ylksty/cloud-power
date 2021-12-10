package com.bjpowernode.springcloud.service;

import com.bjpowernode.springcloud.constants.Constant;
import com.bjpowernode.springcloud.model.Orders;
import com.bjpowernode.springcloud.model.ResultObject;
import feign.hystrix.FallbackFactory;
import org.springframework.stereotype.Component;

@Component
public class OrdersRemoteClientFallBackFactory implements FallbackFactory<OrdersRemoteClient> {

    @Override
    public OrdersRemoteClient create(Throwable throwable) {
        return new OrdersRemoteClient() {
            @Override
            public ResultObject order(Integer uid, Integer goodsId, Integer buyNum, Orders orders) {
                String message = throwable.getMessage();
                System.out.println("feign远程调用异常：" + message);
                return new ResultObject(Constant.ONE, "服务异常了.");
            }

            @Override
            public ResultObject orders() {
                String message = throwable.getMessage();
                System.out.println("feign远程调用异常：" + message);
                return new ResultObject(Constant.ONE, "服务异常了.");
            }
        };
    }
}