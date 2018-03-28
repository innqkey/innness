package com.huisou.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.huisou.mapper.ResRebateSetPoMapper;
import com.huisou.po.ResRebateSetPo;
import com.huisou.service.ResRebateSetService;
import com.huisou.vo.ResRebateSetVo;

/** 
* @author 作者 :yuhao 
* @version 创建时间：2018年3月22日 下午4:01:13 
* 类说明 
*/
@Service
public class ResRebateSetServiceImpl implements ResRebateSetService {

	@Autowired
	private ResRebateSetPoMapper resRebateSetPoMapper;
	
	@Override
	public List<ResRebateSetVo> findAll(Integer resId,String resType) {
		List<ResRebateSetVo> list = resRebateSetPoMapper.findAll(resId,resType);
		return list;
	}

	@Override
	public void updateOne(ResRebateSetPo resRebateSetPo) {
		// TODO Auto-generated method stub
		resRebateSetPoMapper.updateByPrimaryKeySelective(resRebateSetPo);
	}

	@Override
	public void addOne(ResRebateSetPo resRebateSetPo) {
		// TODO Auto-generated method stub
		resRebateSetPoMapper.insertSelective(resRebateSetPo);
	}

}
