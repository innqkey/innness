package com.huisou.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.huisou.mapper.SayPublisherPoMapper;
import com.huisou.po.SayPublisherPo;
import com.huisou.service.PublisherService;
import com.huisou.vo.PageTemp;
import com.huisou.vo.SayPublisherVo;

/** 
* @author qinkai 
* @date 2018年2月2日
*/
@Service
public class PublisherServiceImpl implements PublisherService{

	@Autowired
	private SayPublisherPoMapper publisherPoMapper;
	
	@Override
	public SayPublisherPo findOne(Integer publisherId) {
		return publisherPoMapper.selectByPrimaryKey(publisherId);
	}

	@Override
	public void updateOne(SayPublisherPo publisherPo) {
		publisherPoMapper.updateByPrimaryKeySelective(publisherPo);
	}

	@Override
	public void addOne(SayPublisherPo publisherPo) {
		publisherPoMapper.insertSelective(publisherPo);
	}

	@Override
	public void deleteOne(List<String> publisherId) {
		publisherPoMapper.delete(publisherId);
	}

	@Override
	public PageInfo<SayPublisherVo> findAll(Map<String, String> maps, PageTemp pageTemp) {
		PageHelper.startPage(pageTemp.getPageNum(), pageTemp.getPageSize());
		List<SayPublisherVo> poList = publisherPoMapper.findAll(maps);
		return new PageInfo<SayPublisherVo>(poList);
	}

}
