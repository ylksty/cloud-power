package com.bjpowernode.controller;

import com.bjpowernode.model.Product;
import com.bjpowernode.service.ProductService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
public class ProductController {

    @Autowired
    private ProductService productService;

    @PostMapping("/product/reduceStock")
    public Product reduceStock(@RequestParam("productId") Integer productId, @RequestParam("amount") Integer amount) throws Exception {

        log.info("减库存请求, 商品:{}, 价格:{}", productId, amount);

        return productService.reduceStock(productId, amount);
    }
}
