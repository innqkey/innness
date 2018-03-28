package com.huisou.mapper;

import java.util.List;
import java.util.Map;

import com.huisou.po.RebateRecordPo;

public interface RebateRecordPoMapper {
    int deleteByPrimaryKey(Integer rebateRecordId);

    int insert(RebateRecordPo record);

    int insertSelective(RebateRecordPo record);

    RebateRecordPo selectByPrimaryKey(Integer rebateRecordId);

    int updateByPrimaryKeySelective(RebateRecordPo record);

    int updateByPrimaryKey(RebateRecordPo record);
    
    List<RebateRecordPo> findRebateRecordByMap(Map paras);
}