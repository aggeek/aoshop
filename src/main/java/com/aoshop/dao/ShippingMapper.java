package com.aoshop.dao;

import com.aoshop.pojo.Shipping;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface ShippingMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Shipping record);

    int insertSelective(Shipping record);

    Shipping selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Shipping record);

    int updateByPrimaryKey(Shipping record);

    int deleteByShippingIdUserId(@Param("shippingId") Integer shippingId, @Param("userId") Integer userId);

    int updateByShipping(Shipping shipping);

    Shipping selectByShippingIdUserId(@Param("userId") Integer userId, @Param("shippingId") Integer shippingId);
    List<Shipping> selectByUserId(Integer userId);
}