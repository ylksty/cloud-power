package com.bjpowernode.springcloud.service;

import com.bjpowernode.springcloud.constants.Constant;
import com.bjpowernode.springcloud.model.ResultObject;
import feign.hystrix.FallbackFactory;
import org.springframework.stereotype.Component;

@Component
public class GoodsRemoteClientFallBackFactory implements FallbackFactory<GoodsRemoteClient> {

    @Override
    public GoodsRemoteClient create(Throwable throwable) {
        return new GoodsRemoteClient() {
            @Override
            public ResultObject goods() {
                String message = throwable.getMessage();
                System.out.println("feign远程调用异常：" + message);
                return new ResultObject(Constant.ONE, "服务异常了.");
            }

            @Override
            public ResultObject goodsDetail(Integer id) {
                String message = throwable.getMessage();
                System.out.println("feign远程调用异常：" + message);
                return new ResultObject(Constant.ONE, "服务异常了.");
            }

            @Override
            public ResultObject decrByStore(Integer id, Integer buyNum) {
                String message = throwable.getMessage();
                System.out.println("feign远程调用异常：" + message);
                return new ResultObject(Constant.ONE, "服务异常了.");
            }
        };
    }
}