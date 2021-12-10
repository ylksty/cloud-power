package com.bjpowernode.service.impl;

import com.bjpowernode.mapper.AccountMapper;
import com.bjpowernode.model.Account;
import com.bjpowernode.service.AccountService;
import io.seata.core.context.RootContext;
import io.seata.rm.tcc.api.BusinessActionContext;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

@Slf4j
@Service
public class AccountServiceImpl implements AccountService {

    @Autowired
    private AccountMapper accountMapper;

    @Override
    public void reduceBalance(Integer userId, BigDecimal money) {
        log.info("[reduceBalance] 当前 XID: {}", RootContext.getXID());

        // 检查余额
        Account account = accountMapper.selectAccountByUserId(userId);
        if (account.getBalance().doubleValue() < money.doubleValue()) {
            throw new RuntimeException("余额不足");
        }

        // 扣除余额
        int updateCount = accountMapper.reduceBalance(userId, money);
        // 扣除成功
        if (updateCount == 0) {
            throw new RuntimeException("余额不足");
        }
        log.info("[reduceBalance] 扣除用户 {} 余额成功", userId);
    }

    /**
     * tcc服务（confirm）方法
     * 可以空确认
     *
     * @param context 上下文
     * @return boolean
     */
    @Override
    public boolean commitTcc(BusinessActionContext context) {
        log.info("Confirm阶段，AccountServiceImpl, commitTcc --> xid = {}", context.getXid() + ", commitTcc提交成功");
        return true;
    }

    /**
     * tcc服务（cancel）方法
     *
     * @param context 上下文
     * @return boolean
     */
    @Override
    public boolean cancelTcc(BusinessActionContext context) {
        log.info("Cancel阶段，AccountServiceImpl, cancelTcc --> xid = " + context.getXid() + ", cancelTcc提交失败");
        //TODO 这里可以实现中间件、非关系型数据库的回滚操作
        log.info("Cancel阶段，AccountServiceImpl, cancelTcc this data: userId= {}, money = {}", context.getActionContext("userId"), context.getActionContext("money"));

        //进行数据库回滚处理
        Integer userId = (Integer)context.getActionContext("userId");
        BigDecimal money = (BigDecimal)context.getActionContext("money");

        //幂等性问题

        //把余额再加回去
        accountMapper.increaseBalance(userId, money);

        return true;
    }
}