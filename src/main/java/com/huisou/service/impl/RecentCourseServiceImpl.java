package com.huisou.service.impl;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.apache.commons.beanutils.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.huisou.constant.ContextConstant;
import com.huisou.mapper.OrderPoMapper;
import com.huisou.mapper.RecentApplyPoMapper;
import com.huisou.mapper.RecentCoursePoMapper;
import com.huisou.mapper.RegistPoMapper;
import com.huisou.po.OrderPo;
import com.huisou.po.RecentApplyPo;
import com.huisou.po.RecentCoursePo;
import com.huisou.po.RegistPo;
import com.huisou.service.RecentCourseService;
import com.huisou.vo.OrderAndApplyVo;
import com.huisou.vo.PageTemp;
import com.huisou.vo.RecentCourseVo;

/** 
* @author 作者 :yuhao 
* @version 创建时间：2018年1月30日 下午2:47:06 
* 类说明 
*/
@Service
public class RecentCourseServiceImpl implements RecentCourseService{

	@Autowired
	private RecentCoursePoMapper recentCoursePoMapper;
	
	@Autowired
	private RecentApplyPoMapper recentApplyPoMapper;
	
	@Autowired
	private	OrderPoMapper orderPoMapper;
	
	
	
	@Override
	public void add(RecentCoursePo recentCoursePo) {
		// TODO Auto-generated method stub
		recentCoursePoMapper.insertSelective(recentCoursePo);
	}

	@Override
	public void update(RecentCoursePo recentCoursePo) {
		// TODO Auto-generated method stub
		recentCoursePoMapper.updateByPrimaryKeySelective(recentCoursePo);
	}

	@Override
	public PageInfo<RecentCourseVo> search(String courseTitle, String recentCourseTitle, Date startDate, Date endDate,
			PageTemp pageTemp) {
		// TODO Auto-generated method stub
		PageHelper.startPage(pageTemp.getPageNum(), pageTemp.getPageSize());
		List<RecentCourseVo> list = recentCoursePoMapper.search(courseTitle,recentCourseTitle,startDate,endDate,ContextConstant.EXIST_STATUS);
		return new PageInfo<RecentCourseVo>(list);
	}

	@Override
	public RecentCoursePo findOneByrecentCourseId(Integer recentCourseId) {
		// TODO Auto-generated method stub
		RecentCoursePo recentCoursePo = recentCoursePoMapper.selectByPrimaryKey(recentCourseId);
		return recentCoursePo;
	}

	@Override
	public PageInfo<RecentCourseVo> findAllByUserId(Integer userId, Date now,PageTemp pageTemp) {
		PageHelper.startPage(pageTemp.getPageNum(), pageTemp.getPageSize());
		List<RecentCoursePo> list = recentCoursePoMapper.findByAfterDate(now);
		List<RecentCourseVo> voList = new ArrayList<>();
		for (RecentCoursePo recentCoursePo : list) {
			RecentCourseVo recentCourseVo = new RecentCourseVo();
			try {
				BeanUtils.copyProperties(recentCourseVo, recentCoursePo);
			} catch (IllegalAccessException | InvocationTargetException e) {
				e.printStackTrace();
			}
			recentCourseVo.setRemainNum(Integer.parseInt(recentCourseVo.getRecentCourseMaxNum())-Integer.parseInt(recentCourseVo.getRecentCourseApplyNum()));
			List<RecentApplyPo> applyList = recentApplyPoMapper.findByUseridAndRecentCourseId(userId,recentCourseVo.getRecentCourseId());
			if(applyList!=null && applyList.size()!=0){
				recentCourseVo.setIsApply(ContextConstant.YES);
			}else{
				recentCourseVo.setIsApply(ContextConstant.NO);
			}
			voList.add(recentCourseVo);
		}
		return new PageInfo(voList);
	}

	
}
