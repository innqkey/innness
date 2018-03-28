package com.huisou.mapper;

import java.util.List;

import com.huisou.po.UserBankPo;

public interface UserBankPoMapper {
    int deleteByPrimaryKey(Integer userBankId);

    int insert(UserBankPo record);

    int insertSelective(UserBankPo record);

    UserBankPo selectByPrimaryKey(Integer userBankId);

    int updateByPrimaryKeySelective(UserBankPo record);

    int updateByPrimaryKey(UserBankPo record);

	List<UserBankPo> getByUserId(int userId);
}