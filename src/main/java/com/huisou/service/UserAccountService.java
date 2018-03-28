package com.huisou.service;

import java.util.List;

import com.huisou.po.UserAccountPo;
import com.huisou.po.UserBankPo;

/**
* 类说明： 用户账户的service
* @author 
* @version 创建时间：2018年3月22日 下午5:17:38
* 
*/
public interface UserAccountService {


	UserAccountPo getByUserId(int userId);

	void insertUserAccount(List<UserAccountPo> userAccountPos);

	void insertOne(UserAccountPo userAccountPo);


}
