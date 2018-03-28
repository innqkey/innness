package com.huisou.mapper;

import org.apache.ibatis.annotations.Param;

import com.huisou.po.UserAccountPo;

public interface UserAccountPoMapper {
    int deleteByPrimaryKey(Integer userAccountId);

    int insert(UserAccountPo record);

    int insertSelective(UserAccountPo record);

    UserAccountPo selectByPrimaryKey(Integer userAccountId);

    int updateByPrimaryKeySelective(UserAccountPo record);

    int updateByPrimaryKey(UserAccountPo record);


	UserAccountPo getByUserId(int userId);
	
	void changeMoney(@Param("userId")Integer userId,@Param("money")Long money);

	void changeMoneyToWithdraw(@Param("userId")Integer userId,@Param("withdrawAccount")Long withdrawAccount);
	
	void updateAccountByRebate(@Param("userId")Integer userId,@Param("rebateMoney")Long rebateMoney);

	void returnMoney(@Param("userId")Integer userId,@Param("money")Long money);
}