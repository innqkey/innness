package com.huisou.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.huisou.po.ResRebateSetPo;
import com.huisou.vo.ResRebateSetVo;

public interface ResRebateSetPoMapper {
    int deleteByPrimaryKey(Integer resRebateId);

    int insert(ResRebateSetPo record);

    int insertSelective(ResRebateSetPo record);

    ResRebateSetPo selectByPrimaryKey(Integer resRebateId);

    int updateByPrimaryKeySelective(ResRebateSetPo record);

    int updateByPrimaryKey(ResRebateSetPo record);

	List<ResRebateSetVo> findAll(@Param(value="resId")Integer resId,@Param(value="resType")String resType);
	
	List<ResRebateSetPo> findByPara(Map para);
}