package com.bjpowernode.feign;

import com.bjpowernode.model.Product;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(name = "29-seata-distributed-product-service")
public interface FeignProductService {

    /**
     * 减库存
     *
     * @param productId 商品ID
     * @param amount    扣减数量
     * @throws Exception 扣减失败时抛出异常
     */
    @PostMapping("/product/reduceStock")
    Product reduceStock(@RequestParam("productId") Integer productId, @RequestParam("amount") Integer amount);

}