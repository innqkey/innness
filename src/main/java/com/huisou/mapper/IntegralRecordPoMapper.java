package com.huisou.mapper;

import java.util.Date;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.huisou.po.IntegralRecordPo;
import com.huisou.vo.IntegralRecordVo;

public interface IntegralRecordPoMapper {
    int deleteByPrimaryKey(Integer integralRecordId);

    int insert(IntegralRecordPo record);

    int insertSelective(IntegralRecordPo record);

    IntegralRecordPo selectByPrimaryKey(Integer integralRecordId);

    int updateByPrimaryKeySelective(IntegralRecordPo record);

    int updateByPrimaryKey(IntegralRecordPo record);

	List<IntegralRecordVo> search(@Param(value="nickname")String nickname, @Param(value="phone")String phone, @Param(value="startDate")Date startDate, @Param(value="endDate")Date endDate);

	List<IntegralRecordVo> selecAll(Map<String, String> maps);
}