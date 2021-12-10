package com.bjpowernode.feign;

import org.springframework.cloud.openfeign.FeignClient;

@FeignClient(name = "29-seata-distributed-order-service")
public interface FeignOrderService {

    /**
     * 创建订单
     *
     * @param userId 用户ID
     * @param productId 产品ID
     * @return 订单编号
     * @throws Exception 创建订单失败，抛出异常
     */
    Integer createOrder(Integer userId, Integer productId) throws Exception;

}