package com.bjpowernode.springcloud.controller;

import com.bjpowernode.springcloud.constants.Constant;
import com.bjpowernode.springcloud.model.Goods;
import com.bjpowernode.springcloud.model.ResultObject;
import com.bjpowernode.springcloud.service.GoodsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class GoodsController {

    @Autowired
    private GoodsService goodsService;

    //@Value("${info.address}")
    //private String address;

    /**
     * 查询所有商品
     *
     * @return
     */
    @RequestMapping(value = "/service/goods", method = RequestMethod.GET)
    public ResultObject goods() {
        System.out.println("/service/goods -->9100 被执行..........");

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

    /**
     * 查询商品详情
     *
     * @param id
     * @return
     */
    @RequestMapping(value="/service/goods/{id}", method = RequestMethod.GET)
    public ResultObject goodsDetail(@PathVariable("id") Integer id) {
        System.out.println("/service/goods/{id} -->9100 被执行..........id=" + id);
        Goods goods = goodsService.getGoodsById(id);
        return new ResultObject(Constant.ZERO, "查询成功", goods);
    }

    /**
     * 减库存
     *
     * @param id
     * @param buyNum
     * @return
     */
    @RequestMapping(value = "/service/goods/{id}", method = RequestMethod.POST)
    public ResultObject decrByStore (@PathVariable("id") Integer id, @RequestParam("buyNum") Integer buyNum) {
        System.out.println("/service/decrByStore -->9100 被执行..........id=" + id + ", buyNum=" + buyNum);
        //减库存操作
        int result = goodsService.decrByStore(id, buyNum);
        return new ResultObject(result == 1 ? Constant.ZERO : Constant.ONE, result == 1 ? "减库存成功" : "减库存失败");
    }
}