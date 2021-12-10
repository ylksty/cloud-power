package com.bjpowernode.model;

import lombok.Data;

import java.math.BigDecimal;
import java.util.Date;

@Data
public class Product {

    private Integer id;

    private String name;

    private BigDecimal price;

    private Integer stock;

    private Date addTime;

    private Date updateTime;

    private Integer amount;
}