package com.huisou.service;

import java.util.Date;
import java.util.List;
import java.util.Map;

import com.github.pagehelper.PageInfo;
import com.huisou.po.RecentCoursePo;
import com.huisou.vo.OrderAndApplyVo;
import com.huisou.vo.PageTemp;
import com.huisou.vo.RecentCourseVo;

/** 
* @author 作者 :yuhao 
* @version 创建时间：2018年1月30日 下午2:46:56 
* 类说明 
*/
public interface RecentCourseService {

	void add(RecentCoursePo recentCoursePo);

	void update(RecentCoursePo recentCoursePo);

	PageInfo<RecentCourseVo> search(String courseTitle, String recentCourseTitle, Date startDate, Date endDate,
			PageTemp pageTemp);

	RecentCoursePo findOneByrecentCourseId(Integer recentCourseId);

	PageInfo<RecentCourseVo> findAllByUserId(Integer userId, Date now,PageTemp pageTemp);

}
