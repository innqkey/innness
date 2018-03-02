package com.huisou.service.impl;

import java.util.Date;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.huisou.constant.ContextConstant;
import com.huisou.mapper.OrderDetailPoMapper;
import com.huisou.mapper.OrderPoMapper;
import com.huisou.mapper.RecentApplyPoMapper;
import com.huisou.mapper.RecentCoursePoMapper;
import com.huisou.mapper.RegistPoMapper;
import com.huisou.po.OrderDetailPo;
import com.huisou.po.OrderPo;
import com.huisou.po.RecentApplyPo;
import com.huisou.po.RecentCoursePo;
import com.huisou.po.RegistPo;
import com.huisou.service.RecentApplyService;
import com.huisou.vo.OrderAndApplyVo;
import com.huisou.vo.PageTemp;
import com.huisou.vo.RecentApplyVo;

/** 
* @author 作者 :yuhao 
* @version 创建时间：2018年1月30日 下午4:38:24 
* 类说明 
*/
@Service
public class RecentApplyServiceImpl implements RecentApplyService{

	@Autowired
	private RecentApplyPoMapper recentApplyPoMapper;
	
	@Autowired
	private	OrderPoMapper orderPoMapper;
	
	@Autowired
	private OrderDetailPoMapper orderDetailPoMapper;
	
	@Autowired
	private RecentCoursePoMapper recentCoursePoMapper;
	
	@Autowired
	private RegistPoMapper registPoMapper;
	
	@Override
	public PageInfo<RecentApplyVo> findRecentApplyByrecentCourseId(Integer recentCourseId, PageTemp pageTemp) {
		// TODO Auto-generated method stub
		PageHelper.startPage(pageTemp.getPageNum(), pageTemp.getPageSize());
		List<RecentApplyVo> list = recentApplyPoMapper.findRecentApplyByrecentCourseId(recentCourseId);
		for (RecentApplyVo recentApplyVo : list) {
			recentApplyVo.setCardNum(recentApplyVo.getCardNum()+"("+recentApplyVo.getCardTypeName()+")");
		}
		return new PageInfo<RecentApplyVo>(list);
	}

	@Override
	public void add(RecentApplyPo recentApplyPo) {
		// TODO Auto-generated method stub
		recentApplyPoMapper.insertSelective(recentApplyPo);
	}

	@Override
	public void addRecentApply(Integer userId, Integer courseId, Integer recentCourseId) {
		RecentCoursePo recentCoursePo = recentCoursePoMapper.selectByPrimaryKey(recentCourseId);
		List<OrderPo> list = orderPoMapper.findByUserIdAndResId(userId, courseId, "KC", ContextConstant.PAY_STATUS_SUCCESS);
		if(list!=null && list.size()!=0){
			List<Integer> registList = orderDetailPoMapper.findAll(list.get(0).getOrderId());
			for (Integer registId : registList) {
				RecentApplyPo recentApplyPo = new RecentApplyPo();
				recentApplyPo.setOrderId(list.get(0).getOrderId());
				recentApplyPo.setUserId(userId);
				recentApplyPo.setRegistId(registId);
				recentApplyPo.setCreateTime(new Date());
				recentApplyPo.setRecentCourseId(recentCourseId);
				recentApplyPo.setRecentCourseStatus(recentCoursePo.getRecentCourseStatus());
				recentApplyPoMapper.insert(recentApplyPo);
			}
			recentCoursePo.setRecentCourseApplyNum(String.valueOf((Integer.parseInt(recentCoursePo.getRecentCourseApplyNum())+list.size())));
			recentCoursePoMapper.updateByPrimaryKeySelective(recentCoursePo);
		}
	}

	@Override
	public List<RecentApplyPo> findByUserIdAndrecentCourseId(Integer userId, Integer recentCourseId) {
		List<RecentApplyPo> list = recentApplyPoMapper.findByUseridAndRecentCourseId(userId,recentCourseId);
		return list;
	}

	@Override
	public List<OrderAndApplyVo> findOrderAndApplyVo(Map map) {
		List<OrderAndApplyVo> list = recentApplyPoMapper.findOrderAndApplyVo(map);
		for (OrderAndApplyVo orderAndApplyVo : list) {
			List<RegistPo> registList = registPoMapper.findByOrderId(orderAndApplyVo.getOrderId());
			orderAndApplyVo.setList(registList);
		}
		return list;
	}
}
