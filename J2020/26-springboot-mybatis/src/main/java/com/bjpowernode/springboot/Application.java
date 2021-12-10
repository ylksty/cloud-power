package com.bjpowernode.springboot;

import com.bjpowernode.springboot.model.Goods;
import com.bjpowernode.springboot.service.GoodsService;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

@SpringBootApplication
public class Application {

    public static void main(String[] args) {
        ApplicationContext context = SpringApplication.run(Application.class, args);

        GoodsService goodsService = context.getBean(GoodsService.class);

        Goods goods = goodsService.getGoodsById(1);
        System.out.println("产品信息：" + goods);
    }
}
