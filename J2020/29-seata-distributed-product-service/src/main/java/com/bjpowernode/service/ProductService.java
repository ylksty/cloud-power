package com.bjpowernode.service;

import com.bjpowernode.model.Product;

public interface ProductService {

    /**
     * 减库存
     *
     * @param productId 商品ID
     * @param amount    扣减数量
     * @throws Exception 扣减失败时抛出异常
     */
    Product reduceStock(Integer productId, Integer amount);

}