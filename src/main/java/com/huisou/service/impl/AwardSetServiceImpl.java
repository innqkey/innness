package com.huisou.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.huisou.mapper.AwardSetPoMapper;
import com.huisou.po.AwardSetPo;
import com.huisou.service.AwardSetService;
import com.huisou.vo.PageTemp;

/** 
* @author 作者 :yuhao 
* @version 创建时间：2018年2月1日 上午9:41:30 
* 类说明 
*/
@Service
public class AwardSetServiceImpl implements AwardSetService{
	
	@Autowired
	private AwardSetPoMapper awardSetPoMapper;

	@Override
	public void add(AwardSetPo awardSetPo) {
		// TODO Auto-generated method stub
		awardSetPoMapper.insertSelective(awardSetPo);
	}

	@Override
	public void update(AwardSetPo awardSetPo) {
		// TODO Auto-generated method stub
		awardSetPoMapper.updateByPrimaryKeySelective(awardSetPo);
	}

	@Override
	public PageInfo<AwardSetPo> search(PageTemp pageTemp) {
		// TODO Auto-generated method stub
		PageHelper.startPage(pageTemp.getPageNum(), pageTemp.getPageSize());
		List<AwardSetPo> list = awardSetPoMapper.search();
		return new PageInfo<AwardSetPo>(list);
	}

	@Override
	public AwardSetPo findOne(Integer awardSetId) {
		AwardSetPo awardSetPo = awardSetPoMapper.selectByPrimaryKey(awardSetId);
		return awardSetPo;
	}

	@Override
	public List<AwardSetPo> findAllByStatus(String existStatus) {
		// TODO Auto-generated method stub
		List<AwardSetPo> list =awardSetPoMapper.findAllByStatus(existStatus);
		return list;
	}

}
