package com.huisou.controller;

import java.util.Date;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.common.DateUtils;
import com.common.ResUtils;
import com.github.pagehelper.PageInfo;
import com.huisou.constant.ContextConstant;
import com.huisou.po.AwardRecordPo;
import com.huisou.service.AwardRecordService;
import com.huisou.vo.AwardRecordVo;
import com.huisou.vo.PageTemp;

/** 
* @author 作者 :yuhao 
* @version 创建时间：2018年1月30日 下午1:51:58 
* 类说明 
*/
@RestController
@RequestMapping(value = "/awardRecord")
public class AwardRecordController extends BaseController{

	@Autowired
	private AwardRecordService awardRecordService;
	
	/**
	 * 添加一条打赏记录
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "/addAwardRecord")
	public String addAwardRecord(HttpServletRequest request){
		try {
			String awardMoney = request.getParameter("awardMoney");
			String resId = request.getParameter("resId");
			String resType = request.getParameter("resType");
			String userId = request.getParameter("userId");
			if(StringUtils.isBlank(awardMoney) || StringUtils.isBlank(resId) || StringUtils.isBlank(resType) || StringUtils.isBlank(userId)){
				return ResUtils.errRes("102", "请求参数错误");
			}
			AwardRecordPo awardRecordPo = new AwardRecordPo();
			awardRecordPo.setAwardMoney(Long.parseLong(awardMoney));
			awardRecordPo.setResId(Integer.parseInt(resId));
			awardRecordPo.setResType(resType);
			awardRecordPo.setUserId(Integer.parseInt(userId));
			awardRecordPo.setCreateTime(new Date());
			awardRecordService.add(awardRecordPo);
			return ResUtils.okRes();
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			return ResUtils.execRes();
		}
	}
	
	/**
	 * 后台根据资源id和类型查看打赏记录
	 * @return
	 */
	@RequestMapping(value = "/findAwardRecordByResid")
	public String findAwardRecordByResid(HttpServletRequest request,PageTemp pageTemp){
		try {
			String resId = request.getParameter("resId");
			String resType = request.getParameter("resType");
			if(StringUtils.isBlank(resId) || StringUtils.isBlank(resType)){
				return ResUtils.errRes("102", "请求参数错误");
			}
			PageInfo<AwardRecordVo> result = awardRecordService.search(Integer.parseInt(resId),resType,pageTemp);
			return ResUtils.okRes(result);
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			return ResUtils.execRes();
		}
	}
	
	
	/**
	 * 打赏列表首页(包括查询按钮)
	 * @param request
	 * @param pageTemp
	 * @return
	 */
	@RequestMapping(value = "/findAll")
	public String findAll(HttpServletRequest request,PageTemp pageTemp){
		try {
			String nickname = request.getParameter("nickname");
			String phone = request.getParameter("phone");
			String startTime = request.getParameter("startTime");
			String endTime = request.getParameter("endTime");
			Date startDate = null;
			Date endDate = null;
			if(StringUtils.isNotBlank(startTime)){
				startDate = DateUtils.format(startTime, DateUtils.Y_M_D);
			}
			if(StringUtils.isNotBlank(endTime)){
				endDate = DateUtils.format(endTime, DateUtils.Y_M_D);
			}
			PageInfo<AwardRecordVo> result = awardRecordService.findAll(nickname, phone,startDate,endDate, pageTemp);
			return ResUtils.okRes(result);
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			return ResUtils.execRes();
		}
	}
	
	/**
	 * 添加打赏
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "/add")
	public String add(HttpServletRequest request){
		try {
			String awardMoney = request.getParameter("awardMoney");
			String resId = request.getParameter("resId");
			String resType = request.getParameter("resType");
			String userToken = request.getParameter("userToken");
			if(StringUtils.isBlank(awardMoney) || StringUtils.isBlank(resId) || StringUtils.isBlank(resType) || StringUtils.isBlank(userToken)){
				return ResUtils.errRes("102", "请求参数错误");
			}
			AwardRecordPo awardRecordPo = new AwardRecordPo();
			Date date = new Date();
			awardRecordPo.setAwardMoney(Long.parseLong(awardMoney));
			awardRecordPo.setResId(Integer.parseInt(resId));
			awardRecordPo.setResType(resType);
			awardRecordPo.setUserId(super.getUserIdByToken(userToken));
			awardRecordPo.setCreateTime(date);
			String awardNo = resType+DateUtils.format(date, DateUtils.YMD)+date.getTime();
			awardRecordPo.setAwardNo(awardNo);
			awardRecordPo.setAwardStatus(ContextConstant.PAY_STATUS_NO);
			AwardRecordVo awardRecordVo = new AwardRecordVo();
			awardRecordService.add(awardRecordPo);
			BeanUtils.copyProperties(awardRecordVo, awardRecordPo);
			awardRecordVo.setOpenid(super.getOpenIdByToken(userToken));
			return ResUtils.okRes(awardRecordVo);
		} catch (Exception e) {
			e.printStackTrace();
			return ResUtils.execRes();
		}
	}
}
