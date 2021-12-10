package com.bjpowernode.model;

import lombok.Data;

import java.math.BigDecimal;
import java.util.Date;

@Data
public class Account {

    private Integer id;

    private Integer userId;

    private BigDecimal balance;

    private Date updateTime;
}