package com.huisou.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.huisou.po.MemberSetPo;

public interface MemberSetPoMapper {
    int deleteByPrimaryKey(Integer memberSetId);

    int insert(MemberSetPo record);

    int insertSelective(MemberSetPo record);

    MemberSetPo selectByPrimaryKey(Integer memberSetId);

    int updateByPrimaryKeySelective(MemberSetPo record);

    int updateByPrimaryKey(MemberSetPo record);

	List<MemberSetPo> findAll();
	
	public MemberSetPo findMinLevelByMoney(@Param(value="money")int  money);
}