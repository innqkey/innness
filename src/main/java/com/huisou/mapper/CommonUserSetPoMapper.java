package com.huisou.mapper;

import java.util.List;

import com.huisou.po.CommonUserSetPo;

public interface CommonUserSetPoMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(CommonUserSetPo record);

    int insertSelective(CommonUserSetPo record);

    CommonUserSetPo selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(CommonUserSetPo record);

    int updateByPrimaryKey(CommonUserSetPo record);

	List<CommonUserSetPo> findByPhone(String phone);
}