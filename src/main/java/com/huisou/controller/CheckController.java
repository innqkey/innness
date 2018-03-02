package com.huisou.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.common.ResUtils;
import com.huisou.po.OrderPo;
import com.huisou.service.OrderService;

/** 
* @author 作者 :yuhao 
* @version 创建时间：2018年2月5日 下午4:46:28 
* 类说明 
*/
@RestController
@RequestMapping(value = "/Check")
public class CheckController extends BaseController{

	@Autowired
	private OrderService orderService;
	
	/**
	 * 校验该用户对资源是否付款过 true:支付成功过; false:支付失败或者未支付
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "/checkIsPay")
	public String checkIsPay(HttpServletRequest request){
		try {
			String resId = request.getParameter("resId");
			String userToken = request.getParameter("userToken");
			String resType = request.getParameter("resType");
			if(StringUtils.isBlank(resId) || StringUtils.isBlank(userToken) || StringUtils.isBlank(resType)){
				return ResUtils.errRes("102", "请求参数错误");
			}
			boolean isPay = orderService.IsPay(super.getUserIdByToken(userToken), Integer.parseInt(resId), resType);
			return ResUtils.okRes(isPay);
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			return ResUtils.execRes();
		}
	}
	
	/**
	 * 校验该用户对该资源是否有未完成的订单 ;有未完成的订单,返回该订单;没有未完成的订单，不返回
	 * @return
	 */
	@RequestMapping(value = "/checkIsOrderNoPay")
	public String checkIsOrderNoPay(HttpServletRequest request){
		try {
			String resId = request.getParameter("resId");
			String userToken = request.getParameter("userToken");
			String resType = request.getParameter("resType");
			if(StringUtils.isBlank(resId) || StringUtils.isBlank(userToken) || StringUtils.isBlank(resType)){
				return ResUtils.errRes("102", "请求参数错误");
			}
			List<OrderPo> list = orderService.findOrderNoPay(super.getUserIdByToken(userToken), Integer.parseInt(resId), resType);
			return ResUtils.okRes(list);
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			return ResUtils.execRes();
		}
	}
	
}
