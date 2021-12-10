package com.bjpowernode.model;

import lombok.Data;

import java.math.BigDecimal;
import java.util.Date;

@Data
public class Orders {

    private Integer id;

    private Integer userId;

    private Integer productId;

    private BigDecimal payAmount;

    private Date addTime;

    private Date updateTime;

}