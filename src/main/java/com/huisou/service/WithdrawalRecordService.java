package com.huisou.service;

import java.util.List;

import com.github.pagehelper.PageInfo;
import com.huisou.po.UniversalPo;
import com.huisou.po.WithdrawalRecordPo;
import com.huisou.vo.MemberWithdrawVo;
import com.huisou.vo.PageTemp;

/**
* 类说明：
* @author 
* @version 创建时间：2018年3月22日 下午5:19:53
* 
*/
public interface WithdrawalRecordService {
	/**
	 * 从提现记录中查询是否有正在审核中的提现记录
	 * @param userId
	 * @return
	 */
	Boolean findNoWithdrawAuditing(int userId);


	PageInfo<WithdrawalRecordPo> findAllByUserId(int userId,PageTemp pageTemp);


	void saveWithdrawRecord(WithdrawalRecordPo withdrawalRecordPo);

	/***
	 * 用于混合取出 佣金管理的界面中内容
	 * @param status 状态
	 * @param phone 手机号
	 * @param username 用户名称
	 * @param pageTemp
	 * @return
	 * @throws Exception 
	 */
	PageInfo<UniversalPo> showVipWithdrawRecord(String username, String phone, Integer status, PageTemp pageTemp) throws Exception;


	WithdrawalRecordPo getOneByRecordId(Integer recordId);


	void withdrawOpeartion(List<Integer> recordIds, Integer status);

	/**
	 * 通过recordis查询到所有的用户的详细信息申请条件
	 * @param recordIds
	 * @return
	 * @throws Exception 
	 */
	List<MemberWithdrawVo> listByRecords(List<Integer> recordIds) throws Exception;

	/**
	 * 根据条件查询提现记录，但是不分页
	 * @param username
	 * @param phone
	 * @param status
	 * @return
	 */
	List<MemberWithdrawVo> showVipWithdrawRecordNoPaging(String username, String phone, Integer status) throws Exception;

}
