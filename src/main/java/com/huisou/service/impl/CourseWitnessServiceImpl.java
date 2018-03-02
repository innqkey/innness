package com.huisou.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.huisou.constant.ContextConstant;
import com.huisou.mapper.CourseWitnessPoMapper;
import com.huisou.po.CourseWitnessPo;
import com.huisou.service.CourseWitnessService;
import com.huisou.vo.PageTemp;

/** 
* @author 作者 :yuhao 
* @version 创建时间：2018年1月29日 下午4:48:58 
* 类说明 
*/
@Service
public class CourseWitnessServiceImpl implements CourseWitnessService{

	@Autowired
	private CourseWitnessPoMapper courseWitnessPoMapper;
	
	@Override
	public void add(CourseWitnessPo courseWitnessPo) {
		// TODO Auto-generated method stub
		courseWitnessPoMapper.insertSelective(courseWitnessPo);
	}

	@Override
	public void update(CourseWitnessPo courseWitnessPo) {
		// TODO Auto-generated method stub
		courseWitnessPoMapper.updateByPrimaryKeySelective(courseWitnessPo);
	}

	@Override
	public PageInfo<CourseWitnessPo> findWitnessByCourseId(Integer courseId, PageTemp pageTemp) {
		// TODO Auto-generated method stub
		PageHelper.startPage(pageTemp.getPageNum(), pageTemp.getPageSize());
		List<CourseWitnessPo> list = courseWitnessPoMapper.findWitnessByCourseId(courseId,ContextConstant.EXIST_STATUS);
		return new PageInfo<>(list);
	}

	@Override
	public CourseWitnessPo findOne(Integer witnessId) {
		// TODO Auto-generated method stub
		CourseWitnessPo courseWitnessPo = courseWitnessPoMapper.selectByPrimaryKey(witnessId);
		return courseWitnessPo;
	}

}
