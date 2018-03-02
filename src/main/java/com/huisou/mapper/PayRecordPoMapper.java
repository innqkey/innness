package com.huisou.mapper;

import java.util.Date;
import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.huisou.po.PayRecordPo;
import com.huisou.vo.PayRecordVo;

public interface PayRecordPoMapper {
    int deleteByPrimaryKey(Integer payRecordId);

    int insert(PayRecordPo record);

    int insertSelective(PayRecordPo record);

    PayRecordPo selectByPrimaryKey(Integer payRecordId);

    int updateByPrimaryKeySelective(PayRecordPo record);

    int updateByPrimaryKey(PayRecordPo record);

	List<PayRecordVo> search(@Param(value="nickname")String nickname, @Param(value="phone")String phone, @Param(value="startDate")Date startDate, @Param(value="endDate")Date endDate);
}