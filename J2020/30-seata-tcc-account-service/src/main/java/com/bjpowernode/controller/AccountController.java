package com.bjpowernode.controller;

import com.bjpowernode.service.AccountService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;

@Slf4j
@RestController
public class AccountController {

    @Autowired
    private AccountService accountService;

    @PostMapping("/account/reduceBalance")
    public void reduceBalance(@RequestParam("userId") Integer userId, @RequestParam("money") BigDecimal money) throws Exception {

        log.info("[reduceBalance] 收到减少余额请求, 用户:{}, 金额:{}", userId, money);
        accountService.reduceBalance(userId, money);
    }
}