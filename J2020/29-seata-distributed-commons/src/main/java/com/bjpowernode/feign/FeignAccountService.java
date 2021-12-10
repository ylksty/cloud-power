package com.bjpowernode.feign;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.math.BigDecimal;

@FeignClient(name = "29-seata-distributed-account-service")
public interface FeignAccountService {

    /**
     * 扣除余额
     *
     * @param userId 用户ID
     * @param money  扣减金额
     * @throws Exception 失败时抛出异常
     */
    @PostMapping("/account/reduceBalance")
    void reduceBalance(@RequestParam("userId") Integer userId, @RequestParam("money") BigDecimal money);

}