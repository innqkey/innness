package com.huisou.service.impl;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.huisou.mapper.PayRecordPoMapper;
import com.huisou.po.PayRecordPo;
import com.huisou.service.PayRecordService;
import com.huisou.vo.PageTemp;
import com.huisou.vo.PayRecordVo;

/** 
* @author 作者 :yuhao 
* @version 创建时间：2018年1月31日 上午10:45:44 
* 类说明 
*/
@Service
public class PayRecordServiceImpl implements PayRecordService{

	@Autowired
	private PayRecordPoMapper payRecordPoMapper;

	@Override
	public PageInfo<PayRecordVo> search(String nickname, String phone, Date startDate, Date endDate,
			PageTemp pageTemp) {
		// TODO Auto-generated method stub
		PageHelper.startPage(pageTemp.getPageNum(), pageTemp.getPageSize());
		List<PayRecordVo> list = payRecordPoMapper.search(nickname,phone,startDate,endDate);
		return new PageInfo<>(list);
	}

	@Override
	public Integer add(PayRecordPo payRecordPo) {
		// TODO Auto-generated method stub
		payRecordPoMapper.insertSelective(payRecordPo);
		return payRecordPo.getPayRecordId();
	}
	
}
