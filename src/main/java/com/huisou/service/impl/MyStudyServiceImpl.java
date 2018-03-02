package com.huisou.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.huisou.mapper.MyStudyPoMapper;
import com.huisou.po.MaterialLevelPo;
import com.huisou.po.MyStudyPo;
import com.huisou.service.MyStudyService;
import com.huisou.vo.MyStudyVo;
import com.huisou.vo.PageTemp;

/** 
* @author qinkai 
* @date 2018年2月6日
*/

@Service
public class MyStudyServiceImpl implements MyStudyService{

	@Autowired
	private MyStudyPoMapper studyPoMapper;
	
	@Override
	public void insertStudyRecord(MyStudyPo studyPo) {
		studyPoMapper.insertSelective(studyPo);
	}

	@Override
	public PageInfo<MyStudyVo> findAll(Map<String, String> maps, PageTemp pageTemp) {
		PageHelper.startPage(pageTemp.getPageNum(), pageTemp.getPageSize());
		List<MyStudyVo> poList = studyPoMapper.findAll(maps);
		return new PageInfo<MyStudyVo>(poList);
	}

}
