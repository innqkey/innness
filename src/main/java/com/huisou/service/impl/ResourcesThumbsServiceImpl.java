package com.huisou.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.huisou.constant.ContextConstant;
import com.huisou.mapper.ResourcesThumbsPoMapper;
import com.huisou.po.ResourcesThumbsPo;
import com.huisou.service.ResourcesThumbsService;
import com.huisou.vo.PageTemp;
import com.huisou.vo.ResourcesEvalVo;
import com.huisou.vo.ResourcesThumbsVo;

/** 
* @author qinkai 
* @date 2018年1月29日
*/

@Service
public class ResourcesThumbsServiceImpl implements ResourcesThumbsService{

	@Autowired
	private ResourcesThumbsPoMapper thumbsPoMapper;
	
	@Override
	public Integer addOne(ResourcesThumbsPo thumbsPo) {
		return thumbsPoMapper.insertSelective(thumbsPo);
	}
	
	@Override
	public void deleteOne(Integer userId,Integer resId) {
		thumbsPoMapper.updateThumbs(userId,resId);
	}
	
	@Override
	public ResourcesThumbsPo findOne(Integer resId, Integer userId) {
		ResourcesThumbsPo thumbsPo = new ResourcesThumbsPo();
		thumbsPo.setResId(resId);
		thumbsPo.setUserId(userId);
		return thumbsPoMapper.findOne(resId,userId);
	}
	
	@Override
	public void updateOne(ResourcesThumbsPo findOne) {
		thumbsPoMapper.updateByPrimaryKeySelective(findOne);
	}
	
	@Override
	public PageInfo<ResourcesThumbsVo> findAll(Map<String, String> maps, PageTemp pageTemp) {
		PageHelper.startPage(pageTemp.getPageNum(), pageTemp.getPageSize());
		List<ResourcesThumbsVo> elalVo = thumbsPoMapper.findAll(maps);
		return new PageInfo<ResourcesThumbsVo>(elalVo);
	}

	@Override
	public List<ResourcesThumbsVo> selectAll(Integer sayId) {
		return thumbsPoMapper.selectAll(sayId);
	}

}
