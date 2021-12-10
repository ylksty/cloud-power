package com.bjpowernode.controller;

import com.bjpowernode.service.OrderService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
public class OrderController {

    @Autowired
    private OrderService orderService;

    /**
     * 下单操作，发起分布式事务
     *
     * @param userId
     * @param productId
     * @return
     * @throws Exception
     */
    @RequestMapping("/order")
    public Integer createOrder(@RequestParam("userId") Integer userId,
                               @RequestParam("productId") Integer productId) throws Exception {

        log.info("[createOrder] 请求下单, 用户:{}, 商品:{}", userId, productId);

        return orderService.createOrder(userId, productId);
    }
}