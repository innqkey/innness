package com.huisou.controller;

import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.common.ResUtils;
import com.huisou.po.MemberSetPo;
import com.huisou.service.MemberSetService;

/** 
* @author 作者 :yuhao 
* @version 创建时间：2018年3月22日 下午4:16:17 
* 类说明 
*/
@RestController
@RequestMapping(value = "/memberSet")
public class MemberSetController extends BaseController{
	
	@Autowired
	private MemberSetService memberSetService;
	
	/**
	 * 查看所有存在的等级
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "/findAll")
	public String findAll(HttpServletRequest request){
		try {
			List<MemberSetPo> list = memberSetService.findAll();
			return ResUtils.okRes(list);
		} catch (Exception e) {
			e.printStackTrace();
			return ResUtils.execRes();
		}
	}
	
	/**
	 * 增加和修改
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "/addAndUpdate")
	public String addAndUpdate(HttpServletRequest request){
		try {
			String memberSetId = request.getParameter("memberSetId");
			String memberSetName = request.getParameter("memberSetName");
			String memberSetMoney = request.getParameter("memberSetMoney");
			String memberSetStatus = request.getParameter("memberSetStatus");
			MemberSetPo memberSetPo = new MemberSetPo();
			if(StringUtils.isNotBlank(memberSetStatus)){
				memberSetPo.setMemberSetStatus(memberSetStatus);
			}else{
				memberSetPo.setMemberSetMoney(Long.parseLong(memberSetMoney));
			}
			memberSetPo.setMemberSetName(memberSetName);
			if(StringUtils.isNotBlank(memberSetId) && StringUtils.isNumeric(memberSetId)){
				memberSetPo.setMemberSetId(Integer.parseInt(memberSetId));
				memberSetService.updateOne(memberSetPo);
			}else{
				memberSetPo.setCreateTime(new Date());
				memberSetService.addOne(memberSetPo);
			}
			return ResUtils.okRes();
		} catch (Exception e) {
			e.printStackTrace();
			return ResUtils.execRes();
		}
	}
	
}
