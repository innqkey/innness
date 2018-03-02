package com.huisou.service;

import com.github.pagehelper.PageInfo;
import com.huisou.po.CourseWitnessPo;
import com.huisou.vo.PageTemp;

/** 
* @author 作者 :yuhao 
* @version 创建时间：2018年1月29日 下午4:48:46 
* 类说明 
*/
public interface CourseWitnessService {

	void add(CourseWitnessPo courseWitnessPo);

	void update(CourseWitnessPo courseWitnessPo);

	PageInfo<CourseWitnessPo> findWitnessByCourseId(Integer courseId, PageTemp pageTemp);

	CourseWitnessPo findOne(Integer witnessId);

}
