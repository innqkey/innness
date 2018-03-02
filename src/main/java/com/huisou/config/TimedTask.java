package com.huisou.config;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.huisou.constant.ContextConstant;
import com.huisou.mapper.RecentApplyPoMapper;
import com.huisou.mapper.RecentCoursePoMapper;
import com.huisou.po.RecentCoursePo;

/** 
* @author 作者 :yuhao 
* @version 创建时间：2018年2月10日 上午11:48:49 
* 类说明 
*/
@Component
public class TimedTask {
	
	@Autowired
	private RecentCoursePoMapper recentCoursePoMapper;
	
	@Autowired
	private RecentApplyPoMapper recentApplyPoMapper;
	
	@Scheduled(cron="0 0 0 * * ?")
	public void updateStatus(){
		Date date = new Date();
		List<RecentCoursePo> list = recentCoursePoMapper.findAll();
		for (RecentCoursePo recentCoursePo : list) {
			Date beginTime = recentCoursePo.getBeginTime();
			if(date.after(beginTime)){
				//设置是否已上课
				recentCoursePo.setRecentCourseStatus(ContextConstant.DELETE_STATUS);
				recentCoursePoMapper.updateByPrimaryKeySelective(recentCoursePo);
				recentApplyPoMapper.updateRecentCourseStatus(recentCoursePo.getRecentCourseId());
			}
		}
	}
}
