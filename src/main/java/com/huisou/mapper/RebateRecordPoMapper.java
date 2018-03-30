package com.huisou.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.huisou.po.RebateRecordPo;
import com.huisou.vo.RebateRecordVo;

public interface RebateRecordPoMapper {
    int deleteByPrimaryKey(Integer rebateRecordId);

    int insert(RebateRecordPo record);

    int insertSelective(RebateRecordPo record);

    RebateRecordPo selectByPrimaryKey(Integer rebateRecordId);

    int updateByPrimaryKeySelective(RebateRecordPo record);

    int updateByPrimaryKey(RebateRecordPo record);
    
    List<RebateRecordPo> findRebateRecordByMap(Map paras);

	List<RebateRecordVo> findRebateRecordByuserId(@Param(value="userId")Integer userId);
}