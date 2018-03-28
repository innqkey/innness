package com.huisou.service.impl;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.common.DateUtils;
import com.common.ResUtils;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.huisou.constant.ContextConstant;
import com.huisou.constant.DictConConstant;
import com.huisou.mapper.CoursePoMapper;
import com.huisou.mapper.OrderDetailPoMapper;
import com.huisou.mapper.OrderPoMapper;
import com.huisou.mapper.RegistPoMapper;
import com.huisou.mapper.VideoAudioPoMapper;
import com.huisou.po.CoursePo;
import com.huisou.po.OrderDetailPo;
import com.huisou.po.OrderPo;
import com.huisou.po.RegistPo;
import com.huisou.po.VideoAudioPo;
import com.huisou.service.OrderService;
import com.huisou.vo.OrderVo;
import com.huisou.vo.PageTemp;
import com.huisou.mapper.UserPoMapper;
import com.huisou.po.UserPo;

@Service
public class OrderServiceImpl implements OrderService {

	@Autowired
	private OrderPoMapper orderPoMapper;
	
	@Autowired
	private VideoAudioPoMapper videoAudioPoMapper;
	
	@Autowired
	private RegistPoMapper registPoMapper;
	
	@Autowired
	private CoursePoMapper coursePoMapper;
	
	@Autowired
	private OrderDetailPoMapper orderDetailPoMapper;
	
	@Autowired
	private UserPoMapper userPoMapper;
	
	@Override
	public PageInfo<OrderVo> orderCourseList(Map paraMap,PageTemp pageTemp) {
		// TODO Auto-generated method stub
		PageHelper.startPage(pageTemp.getPageNum(), pageTemp.getPageSize());
//		List<OrderVo> list = orderPoMapper.orderCourseList(paraMap);
//		return new PageInfo<OrderVo>(list);
		
		List<OrderVo> orderVos = orderPoMapper.orderCourseList(paraMap);
		if (null != orderVos && orderVos.size() > 0){
			for(int i = 0; i < orderVos.size(); i++){
				OrderVo orderVo = orderVos.get(i);
				Integer classmateId = orderVo.getClassmateId();
				if (null != classmateId){
					UserPo findOne = userPoMapper.selectByPrimaryKey(classmateId);
					if (null != findOne){
						orderVo.setClassmateName(findOne.getUsername());
						orderVo.setClassmatePhone(findOne.getPhone());
					}
				}
			}
		}
		return new PageInfo<OrderVo>(orderVos);
		
	}
	@Override
	public PageInfo<OrderVo> orderVideoAudioList(Map paraMap, PageTemp pageTemp) {
		PageHelper.startPage(pageTemp.getPageNum(), pageTemp.getPageSize());
		List<OrderVo> list = orderPoMapper.orderVideoAudioList(paraMap);
		return new PageInfo<OrderVo>(list);
	}
	@Override
	public boolean IsPay(Integer userId, Integer resId, String resType) {
		if("SP".equals(resType)){
		VideoAudioPo videoAudioPo =	videoAudioPoMapper.selectByPrimaryKey(resId);
			List<OrderPo> orderPoList = orderPoMapper.findByUserIdAndResId(userId, videoAudioPo.getCourseId(), "KC",ContextConstant.PAY_STATUS_SUCCESS);
			if(orderPoList != null && orderPoList.size()!=0){
				return true;
			}
		}
		List<OrderPo> orderList = orderPoMapper.findByUserIdAndResId(userId, resId, resType,ContextConstant.PAY_STATUS_SUCCESS);
		if(orderList != null && orderList.size()!=0){
			return true;
		}
		return false;
	}
	
	@Override
	public List<OrderPo> findOrderNoPay(Integer userId, Integer resId, String resType) {
		// TODO Auto-generated method stub
		List<OrderPo> orderPoList = orderPoMapper.findByUserIdAndResId(userId, resId, resType,ContextConstant.PAY_STATUS_NO);
		return orderPoList;
	}
	@Override
	public List<OrderVo> findOrderByParamers(Map map) {
		// TODO Auto-generated method stub
		List<OrderVo> list = orderPoMapper.orderCourseList(map);
		for (OrderVo orderVo : list) {
			List<RegistPo> registList = registPoMapper.findByOrderId(orderVo.getOrderId());
			orderVo.setList(registList);
		}
		return list;
	}
	@Override
	public OrderPo selectOne(Integer orderId) {
		// TODO Auto-generated method stub
		return orderPoMapper.selectByPrimaryKey(orderId);
	}
	@Override
	public void update(OrderPo orderPo) {
		// TODO Auto-generated method stub
		orderPoMapper.updateByPrimaryKeySelective(orderPo);
	}
	@Override
	public OrderPo registCourse(Integer userId, Integer courseId, String phone, List<RegistPo> list) {
		// TODO Auto-generated method stub
		CoursePo coursePo = coursePoMapper.selectByPrimaryKey(courseId);
		OrderPo orderPo = new OrderPo();
		Date date = new Date();
		String  orderNo = "KC" + DateUtils.format(date, DateUtils.YMD) + date.getTime();
		orderPo.setOrderNo(orderNo);
		orderPo.setResId(courseId);
		orderPo.setResType("KC");
		orderPo.setOrderPay(new BigDecimal(coursePo.getCoursePrice()*list.size()));
		orderPo.setPayStatus(ContextConstant.PAY_STATUS_NO);
		orderPo.setUserId(userId);
//		orderPo.setPhone(phone);
		orderPo.setOrderPersonNum(list.size());
		orderPo.setCreateTime(date);
		orderPo.setOrderStatus(ContextConstant.EXIST_STATUS);
		orderPoMapper.insertSelective(orderPo);
		for (RegistPo registPo : list) {
			registPo.setUserId(userId);
			registPo.setCreateTime(date);
			registPo.setRegistStatus(ContextConstant.EXIST_STATUS);
			registPo.setCardTypeName(DictConConstant.getDicName("cardType", registPo.getCardType()));
			registPoMapper.insertSelective(registPo);
			OrderDetailPo orderDetailPo = new OrderDetailPo();
			orderDetailPo.setCourseId(courseId);
			orderDetailPo.setCreateTime(date);
			orderDetailPo.setOrderId(orderPo.getOrderId());
			orderDetailPo.setOrderDetailStatus(ContextConstant.EXIST_STATUS);
			orderDetailPo.setRegistId(registPo.getRegistId());
			orderDetailPo.setUserId(userId);
			orderDetailPoMapper.insertSelective(orderDetailPo);
		}
		return orderPo;
	}
	@Override
	public Integer add(OrderPo orderPo) {
	    orderPoMapper.insertSelective(orderPo);
		return orderPo.getOrderId();
	}
	
	/**
	 * 通过支付
	 */
	@Override
	public OrderPo findByoutTradeNo(String tradeNo) {
		return orderPoMapper.findByTradeNo(tradeNo);
		
	}

	/**
	 * 所有支付成功的定单
	 */
	@Override
	public List<OrderVo> successOrderCourseList(Map paraMap) {
		
		List<OrderVo> list = orderPoMapper.orderCourseList(paraMap);
		return list;
	}
}
