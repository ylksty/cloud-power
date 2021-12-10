package com.bjpowernode.springcloud.controller;

import com.bjpowernode.springcloud.service.GoodsService;
import com.bjpowernode.springcloud.constants.Constant;
import com.bjpowernode.springcloud.model.Goods;
import com.bjpowernode.springcloud.model.ResultObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class GoodsController {

    @Autowired
    private GoodsService goodsService;

    /**
     * 查询所有商品
     *
     * @return
     */
    @RequestMapping("/service/goods")
    public ResultObject goods() {
        System.out.println("9200 被执行..........");


        /*try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }*/

        /*String str = null;
        if (str == null) {
            throw new RuntimeException("Goods服务异常了.");
        }*/

        List<Goods> goodsList = goodsService.getAllGoods();
        return new ResultObject(Constant.ZERO, "查询成功", goodsList);
    }
}