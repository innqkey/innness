package com.huisou.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.huisou.po.CommonUserPo;

public interface CommonUserPoMapper {
    int deleteByPrimaryKey(Integer commonUserId);

    int insert(CommonUserPo record);

    int insertSelective(CommonUserPo record);

    CommonUserPo selectByPrimaryKey(Integer commonUserId);

    int updateByPrimaryKeySelective(CommonUserPo record);

    int updateByPrimaryKey(CommonUserPo record);

	List<CommonUserPo> findAll();

	CommonUserPo search(@Param(value="username")String username,@Param(value="password")String password);
}