package com.huisou.service.impl;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.common.ResUtils;
import com.huisou.mapper.UserAccountPoMapper;
import com.huisou.mapper.UserBankPoMapper;
import com.huisou.mapper.WithdrawalRecordPoMapper;
import com.huisou.po.UserAccountPo;
import com.huisou.po.UserBankPo;
import com.huisou.po.WithdrawalRecordPo;
import com.huisou.service.UserBankService;

/**
* 类说明：
* @author 
* @version 创建时间：2018年3月22日 下午5:23:40
* 
*/
@Service
public class UserBankServiceImpl implements UserBankService {
	@Autowired
	private UserBankPoMapper userBankPoMapper;
	
	@Autowired
	private WithdrawalRecordPoMapper withdrawalRecordPoMapper;
	@Autowired
	private UserAccountPoMapper userAccountPoMapper;
	

	@Override
	public List<UserBankPo> getByUserId(int userId) {
		return userBankPoMapper.getByUserId(userId);
	}

	/**
	 * 添加或者更新银行卡的信息
	 * 同时创建提现记录
	 */
	@Transactional
	@Override
	public UserBankPo saveAndUpdate(UserBankPo userBankPo, WithdrawalRecordPo withdrawalRecordPo) {
		//创建或者跟新银行卡的信息
		if (userBankPo.getUserBankId() != null) {
			userBankPoMapper.updateByPrimaryKey(userBankPo);
		}else {
			userBankPo.setCreateTime(new Date());
			userBankPoMapper.insertSelective(userBankPo);
		}
		
		//插入新的更新记录
		if (userBankPo !=  null && userBankPo.getUserBankId() != null) {
			withdrawalRecordPo.setUserBankId(userBankPo.getUserBankId());
			List<WithdrawalRecordPo> withdrawalRecordList = withdrawalRecordPoMapper.findAllByStatus(withdrawalRecordPo.getUserId(),1);;
			if (withdrawalRecordList != null && withdrawalRecordList.size() > 0) {
				return null;
			}
			
			synchronized (this) {
				UserAccountPo userAccountPo = userAccountPoMapper.getByUserId(withdrawalRecordPo.getUserId());
				//判断提现的金额是否大于可提现的金额
				if ((userAccountPo.getAccountMoney()>0 )&& (userAccountPo.getAccountMoney() - withdrawalRecordPo.getWithdrawAccount() >= 0 )) {
					userAccountPoMapper.changeMoneyToWithdraw(withdrawalRecordPo.getUserId(),withdrawalRecordPo.getWithdrawAccount());
				}
			}
			
			//减去提现金额，放入待提现中去
			withdrawalRecordPo.setCreateTime(new Date());
			withdrawalRecordPoMapper.insertSelective(withdrawalRecordPo);
			
		}
		return userBankPo;
	}
}
