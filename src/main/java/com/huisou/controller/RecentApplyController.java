package com.huisou.controller;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.common.ResUtils;
import com.github.pagehelper.PageInfo;
import com.huisou.po.RecentApplyPo;
import com.huisou.service.RecentApplyService;
import com.huisou.service.RecentCourseService;
import com.huisou.service.RegistService;
import com.huisou.vo.OrderAndApplyVo;
import com.huisou.vo.PageTemp;
import com.huisou.vo.RecentApplyVo;

/** 
* @author 作者 :yuhao 
* @version 创建时间：2018年1月30日 下午4:36:52 
* 类说明 
*/
@RestController
@RequestMapping(value = "/recentApply")
public class RecentApplyController extends BaseController{

	@Autowired
	private RecentApplyService applyService;
	
	@Autowired
	private RegistService registService;
	
	@Autowired
	private RecentCourseService recentCourseService;
	/**
	 *查看报名人 (报名人列表)
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "/findRecentApply")
	public String findRecentApplyByrecentCourseId(HttpServletRequest request,PageTemp pageTemp){
		try {
			String recentCourseId = request.getParameter("recentCourseId");
			if(StringUtils.isBlank(recentCourseId)){
				return ResUtils.errRes("102", "请求参数错误");
			}
			PageInfo<RecentApplyVo> result = applyService.findRecentApplyByrecentCourseId(Integer.parseInt(recentCourseId),pageTemp);
			return ResUtils.okRes(result);
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			return ResUtils.execRes();
		}
	}
	
	
	/**
	 * 添加申请人模块
	 * @param request
	 * @return
	 *//*
	@RequestMapping(value = "/add")
	public String add(HttpServletRequest request){
		try {
			Map map = super.getPara();
			if(StringUtils.isBlank(map.get("userId").toString()) || StringUtils.isBlank(map.get("cardType").toString()) || StringUtils.isBlank(map.get("cardTypeName").toString()) || StringUtils.isBlank(map.get("cardNum").toString()) || StringUtils.isBlank(map.get("cardPhone").toString()) || StringUtils.isBlank(map.get("orderId").toString()) || StringUtils.isBlank(map.get("recentCourseId").toString())){
				return ResUtils.errRes("102", "请求参数错误");
			}
			RegistPo registPo = new RegistPo();
			registPo.setUserId(Integer.parseInt(map.get("userId").toString()));
			registPo.setCardNum(map.get("cardNum").toString());
			registPo.setCardType(map.get("cardType").toString());
			registPo.setCardTypeName(map.get("cardTypeName").toString());
			registPo.setCardPhone(map.get("cardPhone").toString());
			registPo.setCreateTime(new Date());
			registPo.setRegistStatus(ContextConstant.EXIST_STATUS);
			Integer registId = registService.add(registPo);
			
			RecentApplyPo recentApplyPo = new RecentApplyPo();
			recentApplyPo.setRegistId(registId);
			recentApplyPo.setOrderId(Integer.parseInt(map.get("orderId").toString()));
			recentApplyPo.setCreateTime(new Date());
			RecentCoursePo recentCoursePo = recentCourseService.findOneByrecentCourseId(Integer.parseInt(map.get("recentCourseId").toString()));
			recentApplyPo.setRecentCourseId(recentCoursePo.getRecentCourseId());
			recentApplyPo.setRecentCourseStatus(recentCoursePo.getRecentCourseStatus());
			
			applyService.add(recentApplyPo);
			
			return ResUtils.okRes();
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			return ResUtils.execRes();
		}
	}*/
	
	/**
	 * 申请近期课程
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "/addRecentApply")
	public String addRecentApply(HttpServletRequest request){
		try {
			String userToken = request.getParameter("userToken");
			String courseId = request.getParameter("courseId");
			String recentCourseId = request.getParameter("recentCourseId");
			if(StringUtils.isBlank(userToken) || StringUtils.isBlank(courseId) || StringUtils.isBlank(recentCourseId)){
				return ResUtils.errRes("102", "请求参数错误");
			}
			 List<RecentApplyPo> list = applyService.findByUserIdAndrecentCourseId(super.getUserIdByToken(userToken),Integer.parseInt(recentCourseId));
			if(list!=null && list.size()!=0){
				return ResUtils.errRes("108", "该用户已经申请过");
			}
			applyService.addRecentApply(super.getUserIdByToken(userToken),Integer.parseInt(courseId),Integer.parseInt(recentCourseId));
			return ResUtils.okRes();
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			return ResUtils.execRes();
		}
	}
	
	/**
	 * 获取该用户已申请的未上课或已经上的订单(userId:用户id;recentCourseStatus:课程状态：1-未上课；2-已上课)
	 * @param request
	 * @return
	 */
	/*@RequestMapping(value = "/findOrderAndRecentCourseByUserId")
	public String findOrderAndRecentCourseByUserId(HttpServletRequest request){
		try {
			Map map =super.getPara();
			List<OrderAndApplyVo> list = applyService.findOrderAndApplyVo(map);
			return ResUtils.okRes(list);
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			return ResUtils.execRes();
		}
	}*/
	
}
