package com.huisou.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.huisou.po.OrderDetailPo;

public interface OrderDetailPoMapper {
    int deleteByPrimaryKey(Integer orderDetailId);

    int insert(OrderDetailPo record);

    int insertSelective(OrderDetailPo record);

    OrderDetailPo selectByPrimaryKey(Integer orderDetailId);

    int updateByPrimaryKeySelective(OrderDetailPo record);

    int updateByPrimaryKey(OrderDetailPo record);

	List<Integer> findAll(@Param(value="orderId")Integer orderId);
}