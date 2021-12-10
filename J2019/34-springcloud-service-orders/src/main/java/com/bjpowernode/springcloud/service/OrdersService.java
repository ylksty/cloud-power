package com.bjpowernode.springcloud.service;

import com.bjpowernode.springcloud.model.Orders;

import java.util.List;

public interface OrdersService {

    public int addOrders(Orders orders);

    public List<Orders> orders();
}