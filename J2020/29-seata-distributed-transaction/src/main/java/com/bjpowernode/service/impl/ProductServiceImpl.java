package com.bjpowernode.service.impl;

import com.baomidou.dynamic.datasource.annotation.DS;
import com.bjpowernode.mapper.ProductMapper;
import com.bjpowernode.model.Product;
import com.bjpowernode.service.ProductService;
import io.seata.core.context.RootContext;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class ProductServiceImpl implements ProductService {

    @Autowired
    private ProductMapper productMapper;

    @Override
    @DS(value = "product-ds")
    public Product reduceStock(Integer productId, Integer amount) throws Exception {
        log.info("当前 XID: {}", RootContext.getXID());

        // 检查库存
        Product product = productMapper.selectByPrimaryKey(productId);
        if (product.getStock() < amount) {
            throw new Exception("库存不足");
        }

        // 扣减库存
        int updateCount = productMapper.reduceStock(productId, amount);
        // 扣除成功
        if (updateCount == 0) {
            throw new Exception("库存不足");
        }

        // 扣除成功
        log.info("扣除 {} 库存成功", productId);
        return product;
    }
}