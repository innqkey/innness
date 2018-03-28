package com.huisou.service.impl;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.common.ConvertUtil;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.huisou.mapper.UserAccountPoMapper;
import com.huisou.mapper.WithdrawalRecordPoMapper;
import com.huisou.po.UniversalPo;
import com.huisou.po.UserAccountPo;
import com.huisou.po.WithdrawalRecordPo;
import com.huisou.service.WithdrawalRecordService;
import com.huisou.vo.MemberWithdrawVo;
import com.huisou.vo.PageTemp;

/**
* 类说明：
* @author 
* @version 创建时间：2018年3月22日 下午5:22:41
* 
*/
@Service
public class WithdrawalRecorServiceImpl implements WithdrawalRecordService {
	@Autowired
	private WithdrawalRecordPoMapper withdrawalRecordPoMapper;
	@Autowired
	private UserAccountPoMapper userAccountPoMapper;
	
	
	@Override
	public Boolean findNoWithdrawAuditing(int userId) {
		List<WithdrawalRecordPo> withdrawalRecordList = withdrawalRecordPoMapper.findAllByStatus(userId,1);;
		boolean result = false;
		if (withdrawalRecordList != null && withdrawalRecordList.size() > 0) {
			result = true;
		}
		
		return result;
	}

	@Override
	public PageInfo<WithdrawalRecordPo> findAllByUserId(int userId,PageTemp pageTemp) {
		PageHelper.startPage(pageTemp.getPageNum(), pageTemp.getPageSize());
		List<WithdrawalRecordPo> list = withdrawalRecordPoMapper.findAllByStatus(userId,null);
		PageInfo<WithdrawalRecordPo> pageInfo = new PageInfo<>(list);
		return pageInfo;
	}

	/**
	 * 提交之前判断用户用户是否存在正在申请中的项目,如果存在就不插入,提交申请成功的话，
	 * 将可以提现的钱减去 ，然后放到待提现的金额中去。
	 */
	@Override
	@Transactional
	public void saveWithdrawRecord(WithdrawalRecordPo withdrawalRecordPo) {
		List<WithdrawalRecordPo> withdrawalRecordList = withdrawalRecordPoMapper.findAllByStatus(withdrawalRecordPo.getUserId(),1);;
		if (withdrawalRecordList != null && withdrawalRecordList.size() > 0) {
			return;
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

	@Override
	public PageInfo<UniversalPo> showVipWithdrawRecord(String username, String phone, Integer status,PageTemp pageTemp) throws Exception {
		PageHelper.startPage(pageTemp.getPageNum(), pageTemp.getPageSize());
		List<UniversalPo> universalPos = withdrawalRecordPoMapper.showVipWithdrawRecord(username,phone,status);
		PageInfo<UniversalPo> pageInfo = new PageInfo<>(universalPos);
		return pageInfo;
	
	}

	@Override
	public WithdrawalRecordPo getOneByRecordId(Integer recordId) {
		return withdrawalRecordPoMapper.selectByPrimaryKey(recordId);
	}

	/**
	 * 
	 */
	@Override
	@Transactional
	public synchronized void withdrawOpeartion(List<Integer> recordIds, Integer status) {
		for (Integer recordId : recordIds) {
			WithdrawalRecordPo withdrawalRecordPo = withdrawalRecordPoMapper.selectByPrimaryKey(recordId);
			if (withdrawalRecordPo != null && withdrawalRecordPo.getWithdrawAccount() != null
					&& withdrawalRecordPo.getUserId() != null && "1".equalsIgnoreCase(withdrawalRecordPo.getRecoreStatus())) {
				//成功加减钱，并修改状态
				if (status == 2) {
					withdrawalRecordPoMapper.withdrawOpeartion(recordId, status);
					userAccountPoMapper.changeMoney(withdrawalRecordPo.getUserId(),withdrawalRecordPo.getWithdrawAccount());
				}
				
				//失败修改状态，将钱加到里面去
				if (status == 3) {
					withdrawalRecordPoMapper.withdrawOpeartion(recordId, status);
					userAccountPoMapper.returnMoney(withdrawalRecordPo.getUserId(),withdrawalRecordPo.getWithdrawAccount());
				}
				
			}
		}
	}
	
	
	@Override
	public List<MemberWithdrawVo> listByRecords(List<Integer> recordIds) throws Exception {
		List<UniversalPo> universalPos = withdrawalRecordPoMapper.listByRecords(recordIds);
		List<MemberWithdrawVo> result = ConvertUtil.convertDtoAndVo(universalPos, MemberWithdrawVo.class);
		return result;
	}
		
	@Override
	public List<MemberWithdrawVo> showVipWithdrawRecordNoPaging(String username, String phone, Integer status) throws Exception {
		List<UniversalPo> universalPos = withdrawalRecordPoMapper.showVipWithdrawRecord(username,phone,status);
		List<MemberWithdrawVo> result = ConvertUtil.convertDtoAndVo(universalPos, MemberWithdrawVo.class);
		return result;
	}
}
