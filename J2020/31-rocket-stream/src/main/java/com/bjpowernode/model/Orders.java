package com.bjpowernode.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Orders {

    private Integer id;

    private Integer userId;

    private Integer productId;

    private BigDecimal payAmount;

    private Date addTime;

    private Date updateTime;
}