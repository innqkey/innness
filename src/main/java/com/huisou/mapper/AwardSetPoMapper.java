package com.huisou.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.huisou.po.AwardSetPo;

public interface AwardSetPoMapper {
    int deleteByPrimaryKey(Integer awardSetId);

    int insert(AwardSetPo record);

    int insertSelective(AwardSetPo record);

    AwardSetPo selectByPrimaryKey(Integer awardSetId);

    int updateByPrimaryKeySelective(AwardSetPo record);

    int updateByPrimaryKey(AwardSetPo record);

	List<AwardSetPo> search();

	List<AwardSetPo> findAllByStatus(@Param(value="awardSetStatus")String awardSetStatus);
}