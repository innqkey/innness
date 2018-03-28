package com.huisou.service;

import java.util.Date;
import java.util.List;

import com.github.pagehelper.PageInfo;
import com.huisou.po.CoursePo;
import com.huisou.vo.CourseVo;
import com.huisou.vo.PageTemp;

/** 
* @author 作者 :yuhao 
* @version 创建时间：2018年1月29日 上午10:56:16 
* 类说明 
*/
public interface CourseService {

 Integer addCourse(CoursePo coursePo);

 void updateCourse(CoursePo coursePo);

 PageInfo<CoursePo> search(String courseTitle, Date startDate, Date endDate,PageTemp pageTemp);
 PageInfo<CourseVo> findAllByUserId(Integer userId,PageTemp pageTemp);
CourseVo findOne(Integer courseId);

List<CoursePo> findCourseByUserid(Integer userid);

List<CoursePo> findAll();

void reset();

CoursePo findDefultApply();

}
