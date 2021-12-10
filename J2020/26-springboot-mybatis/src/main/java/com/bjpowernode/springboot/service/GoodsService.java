package com.bjpowernode.springboot.service;

import com.bjpowernode.springboot.mapper.GoodsMapper;
import com.bjpowernode.springboot.model.Goods;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class GoodsService {

    @Autowired
    private GoodsMapper goodsMapper;

    public Goods getGoodsById(Integer id) {
        return goodsMapper.selectByPrimaryKey(id);
    }
}