package com.bjpowernode.service.impl;

import com.bjpowernode.mapper.ProductMapper;
import com.bjpowernode.model.Product;
import com.bjpowernode.service.ProductService;
import io.seata.core.context.RootContext;
import io.seata.rm.tcc.api.BusinessActionContext;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class ProductServiceImpl implements ProductService {

    @Autowired
    private ProductMapper productMapper;

    /**
     * tcc服务（try）方法
     * 也是实际业务方法
     *
     * @param productId 商品ID
     * @param amount    扣减数量
     * @return
     */
    @Override
    public Product reduceStock(Integer productId, Integer amount) {
        log.info("[reduceStock] 当前 XID: {}", RootContext.getXID());

        // 检查库存
        Product product = productMapper.selectByPrimaryKey(productId);
        if (product.getStock() < amount) {
            throw new RuntimeException("库存不足");
        }

        // 减库存
        int updateCount = productMapper.reduceStock(productId, amount);
        // 减库存失败
        if (updateCount == 0) {
            throw new RuntimeException("库存不足");
        }

        // 减库存成功
        log.info("减库存 {} 库存成功", productId);

        return product;
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
        log.info("Confirm阶段，ProductServiceImpl, commitTcc --> xid = " + context.getXid() + ", commitTcc提交成功");
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
        log.info("Cancel阶段，ProductServiceImpl, cancelTcc --> xid = " + context.getXid() + ", cancelTcc提交失败");
        //TODO 这里可以实现中间件、非关系型数据库的回滚操作
        log.info("Cancel阶段，ProductServiceImpl, cancelTcc this data: {}, {}", context.getActionContext("productId"), context.getActionContext("amount"));

        //进行数据库回滚处理
        Integer productId = (Integer)context.getActionContext("productId");
        Integer amount = (Integer)context.getActionContext("amount");

        //把库存再加回去
        productMapper.increaseStock(productId, amount);

        return true;
    }
}