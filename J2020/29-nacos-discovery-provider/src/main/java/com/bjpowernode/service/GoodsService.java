package com.bjpowernode.service;

import com.bjpowernode.mapper.GoodsMapper;
import com.bjpowernode.model.Goods;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GoodsService {

    @Autowired
    private GoodsMapper goodsMapper;

    public List<Goods> getAllGoods() {
        return goodsMapper.selectByAll();
    }
}