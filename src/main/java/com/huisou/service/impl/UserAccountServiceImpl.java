package com.huisou.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.huisou.mapper.UserAccountPoMapper;
import com.huisou.po.UserAccountPo;
import com.huisou.po.UserBankPo;
import com.huisou.service.UserAccountService;

/**
* 类说明：
* @author 
* @version 创建时间：2018年3月22日 下午5:18:23
* 
*/
@Service
public class UserAccountServiceImpl implements UserAccountService{
	@Autowired
	private UserAccountPoMapper userAccountPoMapper;

	@Override
	public UserAccountPo getByUserId(int userId) {
		return userAccountPoMapper.getByUserId(userId);
	}

	@Override
	public void insertUserAccount(List<UserAccountPo> userAccountPos) {
		if (null != userAccountPos && userAccountPos.size() > 0){
			for (UserAccountPo userAccountPo : userAccountPos) {
				if (null == userAccountPoMapper.getByUserId(userAccountPo.getUserId())){
					userAccountPoMapper.insertSelective(userAccountPo);
				}
			}
		}
	}

	@Override
	public void insertOne(UserAccountPo userAccountPo) {
		if (null != userAccountPo){
			if (null == userAccountPoMapper.getByUserId(userAccountPo.getUserId())){
				userAccountPoMapper.insertSelective(userAccountPo);
			}
		}
	}
}
