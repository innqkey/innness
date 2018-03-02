package com.huisou.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.huisou.po.RegistPo;

public interface RegistPoMapper {
    int deleteByPrimaryKey(Integer registId);

    int insert(RegistPo record);

    int insertSelective(RegistPo record);

    RegistPo selectByPrimaryKey(Integer registId);

    int updateByPrimaryKeySelective(RegistPo record);

    int updateByPrimaryKey(RegistPo record);

	List<RegistPo> findByOrderId(@Param(value="orderId")Integer orderId);

	RegistPo findByCardNum(@Param(value="cardNum")String cardNum);

	RegistPo selectByUserId(@Param(value="userId")Integer userId);
}