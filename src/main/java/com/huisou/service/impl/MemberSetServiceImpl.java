package com.huisou.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.huisou.mapper.MemberSetPoMapper;
import com.huisou.po.MemberSetPo;
import com.huisou.service.MemberSetService;

/** 
* @author 作者 :yuhao 
* @version 创建时间：2018年3月22日 下午3:58:56 
* 类说明 
*/
@Service
public class MemberSetServiceImpl implements MemberSetService {

		@Autowired
		private MemberSetPoMapper memberSetPoMapper;
	
	@Override
	public List<MemberSetPo> findAll() {
		List<MemberSetPo> list = memberSetPoMapper.findAll();
		return list;
	}

	@Override
	public void updateOne(MemberSetPo memberSetPo) {
		memberSetPoMapper.updateByPrimaryKeySelective(memberSetPo);
	}

	@Override
	public void addOne(MemberSetPo memberSetPo) {
		memberSetPoMapper.insertSelective(memberSetPo);
	}

	@Override
	public MemberSetPo findOne(Integer memberSetId) {
		return memberSetPoMapper.selectByPrimaryKey(memberSetId);
	}

}
