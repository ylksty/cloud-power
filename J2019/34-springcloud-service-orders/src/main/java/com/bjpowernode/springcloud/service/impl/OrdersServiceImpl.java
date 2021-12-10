package com.bjpowernode.springcloud.service.impl;

import com.bjpowernode.springcloud.mapper.OrdersMapper;
import com.bjpowernode.springcloud.model.Orders;
import com.bjpowernode.springcloud.service.OrdersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrdersServiceImpl implements OrdersService {

    @Autowired
    private OrdersMapper ordersMapper;

    public int addOrders(Orders orders) {
        //下订单
        return ordersMapper.insertSelective(orders);
    }

    public List<Orders> orders() {
        return ordersMapper.selectByAll();
    }
}