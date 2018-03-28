package com.huisou.service;

import java.util.List;

import com.huisou.po.UserBankPo;
import com.huisou.po.WithdrawalRecordPo;

/**
* 类说明：
* @author 
* @version 创建时间：2018年3月22日 下午5:23:20
* 
*/
public interface UserBankService {


	List<UserBankPo> getByUserId(int userId);
	/**
	 * 添加或者更新银行卡的信息
	 * 同时创建提现记录
	 */
	UserBankPo saveAndUpdate(UserBankPo userBankPo, WithdrawalRecordPo withdrawalRecordPo);

}
