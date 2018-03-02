package com.huisou.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.huisou.constant.ContextConstant;
import com.huisou.constant.DictConConstant;
import com.huisou.mapper.ResourcesEvalPoMapper;
import com.huisou.mapper.SayPoMapper;
import com.huisou.po.SayPo;
import com.huisou.service.SayService;
import com.huisou.vo.PageTemp;

/** 
* @author qinkai 
* @date 2018年1月29日
*/
@Service
public class SayServiceImpl implements SayService{

	@Autowired
	private SayPoMapper sayPoMapper;
	@Autowired
	private ResourcesEvalPoMapper evalPoMapper;
	
	@Override
	public void addOne(SayPo sayPo) {
		sayPoMapper.insertSelective(sayPo);
	}
	
	@Override
	public SayPo findOne(Integer sayId) {
		return sayPoMapper.selectByPrimaryKey(sayId);
	}

	@Override
	public void updateOne(SayPo sayPo) {
		sayPoMapper.updateByPrimaryKeySelective(sayPo);
	}

	@Override
	public void deleteOne(List<String> sayId) {
		sayPoMapper.delete(sayId);
	}

	@Override
	public PageInfo<SayPo> findAll(Map<String, String> maps, PageTemp pageTemp) {
		PageHelper.startPage(pageTemp.getPageNum(), pageTemp.getPageSize());
		List<SayPo> poList = sayPoMapper.findAll(maps);
		return new PageInfo<SayPo>(poList);
	}

	@Override
	public void updateNum(Integer sayId, Integer type,Integer num) {
		sayPoMapper.increaseNum(sayId,type,num);
	}

	@Override
	public SayPo selectOne() {
		return sayPoMapper.selectOne();
	}

}
