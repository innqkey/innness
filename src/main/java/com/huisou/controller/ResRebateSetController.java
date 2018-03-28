package com.huisou.controller;

import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.common.JacksonUtil;
import com.common.ResUtils;
import com.huisou.po.ResRebateSetPo;
import com.huisou.service.ResRebateSetService;
import com.huisou.vo.ResRebateSetVo;

/** 
* @author 作者 :yuhao 
* @version 创建时间：2018年3月22日 下午4:05:06 
* 类说明 
*/
@RestController
@RequestMapping(value = "/resRebateSet")
public class ResRebateSetController extends BaseController{
	
	@Autowired
	private ResRebateSetService resRebateSetService;
	
	@RequestMapping(value = "/findAll")
	public String findAll(HttpServletRequest request){
		try {
			String resId = request.getParameter("resId");
			String resType = request.getParameter("resType");
			Integer resIds = 0;
			if(StringUtils.isNotBlank(resId)){
				resIds = Integer.parseInt(resId);
			}
			List<ResRebateSetVo> list = resRebateSetService.findAll(resIds,resType);
			return ResUtils.okRes(list);
		} catch (Exception e) {
			e.printStackTrace();
			return ResUtils.execRes();
		}
	}
	
	/*@RequestMapping(value = "/addAndUpdate")
	public String addAndUpdate(HttpServletRequest request){
		try {
			String resRebateId = request.getParameter("resRebateId");
			String resId = request.getParameter("resId");
			String resType = request.getParameter("resType");
			String memberSetId = request.getParameter("memberSetId");
			String memberSetName = request.getParameter("memberSetName");
			String resRebateMoney = request.getParameter("resRebateMoney");
			if(StringUtils.isBlank(resId) || StringUtils.isBlank(memberSetId) || (!StringUtils.isNumeric(resId)) || (!StringUtils.isNumeric(memberSetId))){
				return ResUtils.errRes("102", "请求参数错误");
			}
			ResRebateSetPo resRebateSetPo = new ResRebateSetPo();
			resRebateSetPo.setResId(Integer.parseInt(resId));
			resRebateSetPo.setResType(resType);
			resRebateSetPo.setMemberSetId(Integer.parseInt(memberSetId));
			resRebateSetPo.setMemberSetName(memberSetName);
			resRebateSetPo.setResRebateMoney(Long.parseLong(resRebateMoney));
			if(StringUtils.isNotBlank(resRebateId) && StringUtils.isNumeric(resRebateId)){
				resRebateSetPo.setResId(Integer.parseInt(resRebateId));
				resRebateSetPo.setUpdateTime(new Date());
				resRebateSetService.updateOne(resRebateSetPo);
			}else{
				resRebateSetPo.setCreateTime(new Date());
				resRebateSetService.addOne(resRebateSetPo);
			}
			return ResUtils.okRes();
		} catch (Exception e) {
			e.printStackTrace();
			return ResUtils.execRes();
		}
	}*/
}
