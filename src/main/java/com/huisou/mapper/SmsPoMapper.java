package com.huisou.mapper;

import org.apache.ibatis.annotations.Param;

import com.huisou.po.SmsPo;

public interface SmsPoMapper {
    int deleteByPrimaryKey(Integer smsId);

    int insert(SmsPo record);

    int insertSelective(SmsPo record);

    SmsPo selectByPrimaryKey(Integer smsId);

    int updateByPrimaryKeySelective(SmsPo record);

    int updateByPrimaryKey(SmsPo record);
    
    int curDateNumByUserId(@Param(value="userId")Integer userId,@Param(value="timeStr")String timeStr);
}