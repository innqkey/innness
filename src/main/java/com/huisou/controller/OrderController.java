package com.huisou.controller;

import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.common.DateUtils;
import com.common.JacksonUtil;
import com.common.ResUtils;
import com.github.pagehelper.PageInfo;
import com.huisou.cache.RedisSmsCodeCache;
import com.huisou.constant.ContextConstant;
import com.huisou.po.OrderPo;
import com.huisou.po.RegistPo;
import com.huisou.po.VideoAudioPo;
import com.huisou.service.OrderService;
import com.huisou.service.RegistService;
import com.huisou.service.VideoAudioService;
import com.huisou.vo.OrderVo;
import com.huisou.vo.PageTemp;

/** 
* @author 作者 :caoxt 
* @version 创建时间：2018年1月30日
* 类说明 :后台订单管理类
*/
@RestController
@RequestMapping(value = "/order")
public class OrderController extends BaseController{

	@Autowired
	private OrderService orderService;
	
	@Autowired
	private RegistService registService;
	
	@Autowired
	private VideoAudioService videoAudioService;
	
	@Autowired
	private RedisSmsCodeCache smsCache;
	
	@RequestMapping(value = "/orderCourseList")
	public String orderCourseList(HttpServletRequest request,PageTemp pageTemp){
		try {
			Map map = this.getPara();
			PageInfo<OrderVo> OrderVo = orderService.orderCourseList(map, pageTemp);
			return ResUtils.okRes(OrderVo);
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			return ResUtils.execRes();
		}
	}
	
	@RequestMapping(value = "/orderVideoAudioList")
	public String orderVideoAudioList(HttpServletRequest request,PageTemp pageTemp){
		try {
			Map map = this.getPara();
			PageInfo<OrderVo> OrderVo = orderService.orderVideoAudioList(map, pageTemp);
			return ResUtils.okRes(OrderVo);
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			return ResUtils.execRes();
		}
	}
	/**
	 * 查询所有的订单课程，或者查询未付费的订单和已支付的订单(userId;payStatus)
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "/findOrderByParamers")
	public String findOrderByParamers(HttpServletRequest request){
		try {
			Map map = this.getPara();
			String userToken = map.get("userToken").toString();
			if(StringUtils.isBlank(userToken)){
				return ResUtils.errRes("102", "请求参数错误");
			}
			map.put("userId", super.getUserIdByToken(userToken));
			List<OrderVo> list = orderService.findOrderByParamers(map);
			return ResUtils.okRes(list);
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			return ResUtils.execRes();
		}
	}
	
	/**
	 * 前台取消订单
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "/delete")
	public String delete(HttpServletRequest request){
		try {
			String userToken = request.getParameter("userToken");
			String orderId = request.getParameter("orderId");
			if(StringUtils.isBlank(orderId) || StringUtils.isBlank(userToken)){
				return ResUtils.errRes("102", "请求参数错误");
			}
			OrderPo orderPo = orderService.selectOne(Integer.parseInt(orderId));
			if(orderPo.getUserId() != super.getUserIdByToken(userToken)){
				return ResUtils.errRes("108", "不是当前订单用户操作");
			}
			orderPo.setOrderStatus(ContextConstant.DELETE_STATUS);
			orderService.update(orderPo);
			return ResUtils.okRes();
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			return ResUtils.execRes();
		}
	}
	
	/**
	 * 报名课程
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "/registCourse")
	public String registCourse(HttpServletRequest request){
		try {
			String userToken = request.getParameter("userToken");
			String code = request.getParameter("code");
			if( StringUtils.isBlank(userToken)){
				return ResUtils.errRes("102", "请求参数错误");
			}
			if( StringUtils.isBlank(code)){
				return ResUtils.errRes("104", "验证码错误");
			}
			String redisCode = smsCache.getSmsCode(userToken);
			if(!code.equals(redisCode)){
				return ResUtils.errRes("104", "验证码错误");
			}
			String registPos = request.getParameter("registPos");
			String courseId = request.getParameter("courseId");
			String phone = request.getParameter("phone");
			if(StringUtils.isBlank(registPos) || StringUtils.isBlank(courseId) || StringUtils.isBlank(phone)){
				return ResUtils.errRes("102", "请求参数错误");
			}
			List<RegistPo> list = JacksonUtil.toListObject(registPos, RegistPo.class);
			if(list==null || list.size()==0){
				return ResUtils.errRes("102", "请求参数错误");
			}
			  /* String msg = registService.isRegist(list);
			   if(StringUtils.isNotBlank(msg)){
				   return ResUtils.errRes("108","身份证号 "+msg+"已报过名");
			   }*/
			   OrderPo orderPo = orderService.registCourse(super.getUserIdByToken(userToken),Integer.parseInt(courseId),phone,list);
			   OrderVo orderVo = new OrderVo();
			   BeanUtils.copyProperties(orderVo, orderPo);
			   orderVo.setOpenid(super.getOpenIdByToken(userToken));
			   return ResUtils.okRes(orderVo);
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			return ResUtils.execRes();
		}
	}
	
	/**
	 * 添加视音频订单
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "/addVideoAudioOrder")
	public String addVideoAudioOrder(HttpServletRequest request){
		try {
			String resId = request.getParameter("resId");
			String resType = request.getParameter("resType");
			String userToken = request.getParameter("userToken");
			if(StringUtils.isBlank(resId) || StringUtils.isBlank(resType) || StringUtils.isBlank(userToken)){
				return ResUtils.errRes("102", "请求参数错误");
			}
			VideoAudioPo videoAudioPo = videoAudioService.findOne(Integer.parseInt(resId));
			OrderPo orderPo = new OrderPo();
			Date date = new Date();
			String  orderNo = resType + DateUtils.format(date, DateUtils.YMD) + date.getTime();
			orderPo.setOrderNo(orderNo);
			orderPo.setOrderPay(videoAudioPo.getVideoAudioPrice());
			orderPo.setPayStatus(ContextConstant.PAY_STATUS_NO);
			orderPo.setOrderStatus(ContextConstant.EXIST_STATUS);
			orderPo.setResId(Integer.parseInt(resId));
			orderPo.setResType(resType);
			orderPo.setUserId(super.getUserIdByToken(userToken));
			orderPo.setCreateTime(date);
			orderService.add(orderPo);
			OrderVo orderVo = new OrderVo();
			BeanUtils.copyProperties(orderVo, orderPo);
			orderVo.setOpenid(super.getOpenIdByToken(userToken));
			return ResUtils.okRes(orderVo);
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			return ResUtils.execRes();
		}
	}
	
	/**
	 * 根据订单id获取报名人
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "/findRegistByorderId")
	public String findRegistByorderId(HttpServletRequest request){
		try {
			String orderId = request.getParameter("orderId");
			if(StringUtils.isBlank(orderId)){
				return ResUtils.errRes("102", "请求参数错误");
			}
			List<RegistPo> list = registService.findRegistByorderId(Integer.parseInt(orderId));
			return ResUtils.okRes(list);
		} catch (Exception e) {
			e.printStackTrace();
			return ResUtils.execRes();
		}
	}
}
