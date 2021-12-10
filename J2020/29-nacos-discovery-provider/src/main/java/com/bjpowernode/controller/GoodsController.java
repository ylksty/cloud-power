package com.bjpowernode.controller;

import com.bjpowernode.service.GoodsService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
public class GoodsController {

    @Autowired
    private GoodsService goodsService;

    @RequestMapping("/goods")
    public Object getAllGoods() {
        log.info("provider --> getAllGoods ......");
        return goodsService.getAllGoods();
    }
}