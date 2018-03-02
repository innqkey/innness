package com.huisou.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.huisou.mapper.RegistPoMapper;
import com.huisou.po.RegistPo;
import com.huisou.service.RegistService;

/** 
* @author 作者 :yuhao 
* @version 创建时间：2018年1月30日 下午8:41:54 
* 类说明 
*/
@Service
public class RegistServiceImpl implements RegistService{

	@Autowired
	private RegistPoMapper registPoMapper;

	@Override
	public Integer add(RegistPo registPo) {
		// TODO Auto-generated method stub
		registPoMapper.insertSelective(registPo);
		return registPo.getRegistId();
	}

	@Override
	public String isRegist(List<RegistPo> list) {
		// TODO Auto-generated method stub
		String msg = "";
		for (RegistPo registPo : list) {
			RegistPo registPo1 = registPoMapper.findByCardNum(registPo.getCardNum());
			if(registPo1 != null){
				msg=msg+registPo.getCardNum()+" ";
			}
		}
		return msg;
	}

	@Override
	public List<RegistPo> findRegistByorderId(Integer orderId) {
		List<RegistPo> list = registPoMapper.findByOrderId(orderId);
		return list;
	}

	@Override
	public RegistPo selectByUserId(Integer userId) {
		return registPoMapper.selectByUserId(userId);
	}
}
