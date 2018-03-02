package com.huisou.service.impl;

import java.lang.reflect.InvocationTargetException;
import java.util.Date;
import java.util.List;

import org.apache.commons.beanutils.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.huisou.constant.ContextConstant;
import com.huisou.mapper.CoursePoMapper;
import com.huisou.mapper.VideoAudioPoMapper;
import com.huisou.po.CoursePo;
import com.huisou.po.VideoAudioPo;
import com.huisou.service.CourseService;
import com.huisou.vo.CourseVo;
import com.huisou.vo.PageTemp;

/** 
* @author 作者 :yuhao 
* @version 创建时间：2018年1月29日 上午10:56:46 
* 类说明 
*/@Service
public class CourseServiceImpl implements CourseService{

	@Autowired
	private CoursePoMapper coursePoMapper;
	
	@Autowired
	private VideoAudioPoMapper videoAudioPoMapper;
	
	@Override
	public Integer addCourse(CoursePo coursePo) {
		coursePoMapper.insertSelective(coursePo);
		//id直接封装到该对象中了，直接获取
		return coursePo.getCourseId();
	}

	@Override
	public void updateCourse(CoursePo coursePo) {
		coursePoMapper.updateByPrimaryKeySelective(coursePo);
	}

	@Override
	public PageInfo<CoursePo> search(String courseTitle, Date startDate, Date endDate,PageTemp pageTemp) {
		PageHelper.startPage(pageTemp.getPageNum(), pageTemp.getPageSize());
		List<CoursePo> list = coursePoMapper.search(courseTitle,startDate,endDate,ContextConstant.EXIST_STATUS);
		return new PageInfo<>(list);
	}

	@Override
	public CourseVo findOne(Integer courseId) {
		CoursePo coursePo = coursePoMapper.selectByPrimaryKey(courseId);
		CourseVo courseVo = new CourseVo();
		try {
			BeanUtils.copyProperties(courseVo, coursePo);
		} catch (IllegalAccessException | InvocationTargetException e) {
			e.printStackTrace();
		}
		List<VideoAudioPo> list = videoAudioPoMapper.findByCourseId(courseId);
		String videoAudioIds = "";
		if(list.size()!=0){
			for (VideoAudioPo videoAudioPo : list) {
				videoAudioIds = videoAudioIds+videoAudioPo.getVideoAudioId()+",";
			}
			videoAudioIds =videoAudioIds.substring(0, videoAudioIds.length()-1);
		}
		courseVo.setVideoaudioIds(videoAudioIds);
		return courseVo;
	}

	@Override
	public List<CoursePo> findCourseByUserid(Integer userid) {
		List<CoursePo> list = coursePoMapper.findCourseByUserid(userid);
		return list;
	}

	@Override
	public List<CoursePo> findAll() {
		List<CoursePo> list = coursePoMapper.search(null, null, null, ContextConstant.EXIST_STATUS);
		return list;
	}

}
