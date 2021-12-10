package com.bjpowernode.mapper;

import com.bjpowernode.model.Account;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.math.BigDecimal;

@Mapper
public interface AccountMapper {

    int deleteByPrimaryKey(Integer id);

    int insert(Account record);

    int insertSelective(Account record);

    Account selectByPrimaryKey(Integer id);

    Account selectAccountByUserId(Integer userId);

    int updateByPrimaryKeySelective(Account record);

    int updateByPrimaryKey(Account record);

    int reduceBalance(@Param("userId") Integer userId, @Param("money") BigDecimal money);

    int increaseBalance(@Param("userId") Integer userId, @Param("money") BigDecimal money);
}