package com.bjpowernode.service;

import java.math.BigDecimal;

public interface AccountService {

    /**
     * 扣除余额
     *
     * @param userId 用户ID
     * @param money  扣减金额
     * @throws Exception 失败时抛出异常
     */
    void reduceBalance(Integer userId, BigDecimal money);

}