package com.bjpowernode.springcloud.controller;

import com.bjpowernode.springcloud.constants.Constant;
import com.bjpowernode.springcloud.model.Orders;
import com.bjpowernode.springcloud.model.ResultObject;
import com.bjpowernode.springcloud.service.OrdersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;

@RestController
public class OrdersController {

    @Autowired
    private OrdersService ordersService;

    /**
     * 查询所有订单
     *
     * @return
     */
    @RequestMapping(value = "/service/orders", method = RequestMethod.GET)
    public ResultObject orders() {
        System.out.println("8100 被执行orders.........");

        List<Orders> ordersList = ordersService.orders();

        return new ResultObject(Constant.ZERO, "查询成功", ordersList);
    }

    /**
     * 下订单
     *
     * @return
     */
    @RequestMapping(value = "/service/order", method = RequestMethod.POST)
    public ResultObject order(@RequestParam("uid") Integer uid,
                              @RequestParam("goodsId") Integer goodsId,
                              @RequestParam("buyNum") Integer buyNum,
                              @RequestBody Orders orders1) {
        System.out.println("8100 被执行..........uid=" + uid + ", goodsId=" +goodsId + ", buyNum="+buyNum + orders1.getBuynum() + orders1.getCreatetime().toString());
        Orders orders = new Orders();
        orders.setBuynum(buyNum);
        orders.setGoodsid(goodsId);
        orders.setUid(uid);
        orders.setCreatetime(new Date());
        ordersService.addOrders(orders);

        return new ResultObject(Constant.ZERO, "下单成功");
    }
}