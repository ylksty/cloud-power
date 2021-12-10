package com.bjpowernode.springcloud.service;

import com.bjpowernode.springcloud.constants.Constant;
import com.bjpowernode.springcloud.model.ResultObject;
import org.springframework.stereotype.Component;

@Component
public class GoodsRemoteClientFallBack implements GoodsRemoteClient {

    /**
     * 备用方法（服务降级方法）
     *
     * @return
     */
    @Override
    public ResultObject goods() {
        return new ResultObject(Constant.ONE,"feign服务调用降级goods()方法");
    }

    /**
     * 备用方法（服务降级方法）
     *
     * @return
     */
    @Override
    public ResultObject goodsDetail(Integer id) {
        return new ResultObject(Constant.ONE,"feign服务调用降级goodsDetail()方法");
    }

    /**
     * 备用方法（服务降级方法）
     *
     * @return
     */
    @Override
    public ResultObject decrByStore(Integer id, Integer buyNum) {
        return new ResultObject(Constant.ONE,"feign服务调用降级decrByStore()方法");
    }
}