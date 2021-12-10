package com.bjpowernode.service;

import com.bjpowernode.model.Product;
import io.seata.rm.tcc.api.BusinessActionContext;
import io.seata.rm.tcc.api.BusinessActionContextParameter;
import io.seata.rm.tcc.api.LocalTCC;
import io.seata.rm.tcc.api.TwoPhaseBusinessAction;

@LocalTCC
public interface ProductService {

    /**
     * 减库存
     *
     * 定义两阶段提交
     * name = reduceStock为一阶段try方法
     * commitMethod = commitTcc 为二阶段确认方法
     * rollbackMethod = cancel 为二阶段取消方法
     * BusinessActionContextParameter注解 可传递参数到二阶段方法
     *
     * @param productId 商品ID
     * @param amount    扣减数量
     * @throws Exception 扣减失败时抛出异常
     */
    @TwoPhaseBusinessAction(name = "reduceStock", commitMethod = "commitTcc", rollbackMethod = "cancelTcc")
    Product reduceStock(@BusinessActionContextParameter(paramName = "productId") Integer productId,
                        @BusinessActionContextParameter(paramName = "amount") Integer amount);

    /**
     * 二阶段提交方法
     *
     * 确认方法、可以另命名，但要保证与commitMethod一致
     * context可以传递try方法的参数
     *
     * @param context 上下文
     * @return boolean
     */
    boolean commitTcc(BusinessActionContext context);

    /**
     * 二阶段回滚方法
     *
     * @param context 上下文
     * @return boolean
     */
    boolean cancelTcc(BusinessActionContext context);

}