package com.huisou.service;

import java.util.List;
import java.util.Map;

import com.github.pagehelper.PageInfo;
import com.huisou.po.RecentApplyPo;
import com.huisou.vo.OrderAndApplyVo;
import com.huisou.vo.PageTemp;
import com.huisou.vo.RecentApplyVo;

/** 
* @author 作者 :yuhao 
* @version 创建时间：2018年1月30日 下午4:38:10 
* 类说明 
*/
public interface RecentApplyService {

	PageInfo<RecentApplyVo> findRecentApplyByrecentCourseId(Integer recentCourseId, PageTemp pageTemp);

	void add(RecentApplyPo recentApplyPo);

	void addRecentApply(Integer userId, Integer courseId, Integer recentCourseId);

	List<RecentApplyPo> findByUserIdAndrecentCourseId(Integer userId, Integer recentCourseId);

	List<OrderAndApplyVo> findOrderAndApplyVo(Map map);

}
