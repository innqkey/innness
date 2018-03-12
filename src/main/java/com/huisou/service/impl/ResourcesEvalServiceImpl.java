package com.huisou.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.huisou.constant.ContextConstant;
import com.huisou.mapper.ResourcesEvalPoMapper;
import com.huisou.po.ResourcesEvalPo;
import com.huisou.service.ResourcesEvalService;
import com.huisou.vo.PageTemp;
import com.huisou.vo.ResourcesEvalVo;

/** 
* @author qinkai 
* @date 2018年1月29日
*/

@Service
public class ResourcesEvalServiceImpl implements ResourcesEvalService{

	@Autowired
	private ResourcesEvalPoMapper evalPoMapper;
	
	@Override
	public Integer addOne(ResourcesEvalPo evalPo) {
		evalPoMapper.insertSelective(evalPo);
		return evalPo.getEvalId();
	}

	@Override
	public PageInfo<ResourcesEvalVo> findAll(Map<String, String> maps, PageTemp pageTemp) {
		PageHelper.startPage(pageTemp.getPageNum(), pageTemp.getPageSize());
		List<ResourcesEvalVo> elalVo = evalPoMapper.findAll(maps);
		return new PageInfo<ResourcesEvalVo>(elalVo);
	}

	@Override
	public void update(ResourcesEvalPo resourcesEvalPo) {
		evalPoMapper.updateByPrimaryKeySelective(resourcesEvalPo);
	}

	@Override
	public List<ResourcesEvalVo> selectAll(Integer sayId) {
		return evalPoMapper.selectAll(sayId);
	}

	@Override
	public void updateStatus(Map params) {
		evalPoMapper.updateStatus(params);
	}

	@Override
	public PageInfo<ResourcesEvalVo> findAllByMap(Map<String, String> map) {
		List<ResourcesEvalVo> elalVo = evalPoMapper.findAllByMap(map);
		return new PageInfo<ResourcesEvalVo>(elalVo);
	}
	
	@Override
	public PageInfo<ResourcesEvalVo> findAllByMapAndStatus(Map<String, String> map,PageTemp pageTemp) {
		PageHelper.startPage(pageTemp.getPageNum(), pageTemp.getPageSize());
		List<ResourcesEvalVo> elalVo = evalPoMapper.findAllByMapAndStatus(map);
		return new PageInfo<ResourcesEvalVo>(elalVo);
	}

	@Override
	public ResourcesEvalPo selectOne(Integer evalId) {
		return evalPoMapper.selectByPrimaryKey(evalId);
	}
}
