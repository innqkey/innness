package com.huisou.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.pagehelper.PageHelper;
import com.huisou.mapper.CommonUserPoMapper;
import com.huisou.po.CommonUserPo;
import com.huisou.service.CommonUserService;
import com.huisou.vo.PageTemp;

/** 
* @author 作者 :yuhao 
* @version 创建时间：2018年2月11日 上午11:24:02 
* 类说明 
*/
@Service
public class CommonUserServiceImpl implements CommonUserService{
	
	@Autowired
	private CommonUserPoMapper commonUserPoMapper;

	@Override
	public void add(CommonUserPo commonUserPo) {
		commonUserPoMapper.insertSelective(commonUserPo);
	}

	@Override
	public void update(CommonUserPo commonUserPo) {
		commonUserPoMapper.updateByPrimaryKeySelective(commonUserPo);
	}

	@Override
	public List<CommonUserPo> findAll(PageTemp pageTemp) {
		PageHelper.startPage(pageTemp.getPageNum(), pageTemp.getPageSize());
		List<CommonUserPo> list = commonUserPoMapper.findAll();
		return list;
	}

	@Override
	public CommonUserPo search(String username,String password) {
		CommonUserPo commonUserPo = commonUserPoMapper.search(username,password);
		return commonUserPo;
	}
}
