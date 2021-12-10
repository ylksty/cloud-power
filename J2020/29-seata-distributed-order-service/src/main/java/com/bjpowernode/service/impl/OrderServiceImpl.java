package com.bjpowernode.service.impl;

import com.bjpowernode.feign.FeignAccountService;
import com.bjpowernode.feign.FeignProductService;
import com.bjpowernode.mapper.OrdersMapper;
import com.bjpowernode.model.Orders;
import com.bjpowernode.model.Product;
import com.bjpowernode.service.OrderService;
import io.seata.core.context.RootContext;
import io.seata.spring.annotation.GlobalTransactional;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

@Slf4j
@Service
public class OrderServiceImpl implements OrderService {

    @Autowired
    private OrdersMapper ordersMapper;

    @Autowired
    private FeignProductService feignProductService;

    @Autowired
    private FeignAccountService feignAccountService;

    @Override
    @GlobalTransactional //seata全局事务注解
    public Integer createOrder(Integer userId, Integer productId) {
        Integer amount = 1; // 购买数量，暂时设为 1

        log.info("[createOrder] 当前 XID: {}", RootContext.getXID());

        // 减库存 （feign的调用） http远程调用
        Product product = feignProductService.reduceStock(productId, amount);

        // 减余额
        feignAccountService.reduceBalance(userId, product.getPrice());

        // 下订单
        Orders order = new Orders();
        order.setUserId(userId);
        order.setProductId(productId);
        order.setPayAmount(product.getPrice().multiply(new BigDecimal(amount)));

        ordersMapper.insertSelective(order);

        log.info("[createOrder] 下订单: {}", order.getId());

        //int a = 10 / 0;

        // 返回订单编号
        return order.getId();
    }
}