package com.bjpowernode.service.impl;

import com.baomidou.dynamic.datasource.annotation.DS;
import com.bjpowernode.mapper.OrdersMapper;
import com.bjpowernode.model.Orders;
import com.bjpowernode.model.Product;
import com.bjpowernode.service.AccountService;
import com.bjpowernode.service.OrderService;
import com.bjpowernode.service.ProductService;
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
    private AccountService accountService;

    @Autowired
    private ProductService productService;

    @Override
    @DS(value = "order-ds")
    @GlobalTransactional //seata全局事务注解
    public Integer createOrder(Integer userId, Integer productId) throws Exception {
        Integer amount = 1; // 购买数量暂时设置为 1

        log.info("当前 XID: {}", RootContext.getXID());

        // 减库存
        Product product = productService.reduceStock(productId, amount);

        // 减余额
        accountService.reduceBalance(userId, product.getPrice());

        // 下订单
        Orders order = new Orders();
        order.setUserId(userId);
        order.setProductId(productId);
        order.setPayAmount(product.getPrice().multiply(new BigDecimal(amount)));

        ordersMapper.insertSelective(order);

        log.info("下订单: {}", order.getId());

        // 返回订单编号
        return order.getId();
    }
}