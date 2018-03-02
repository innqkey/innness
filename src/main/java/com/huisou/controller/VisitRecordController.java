package com.huisou.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.common.ResUtils;
import com.huisou.service.VisitRecordService;
import com.huisou.vo.VisitRecordVo;

/** 
* @author qinkai 
* @date 2018年2月10日
*/

@RestController
@RequestMapping(value = "/visit")
public class VisitRecordController extends BaseController{
	
	@Autowired
	private VisitRecordService visitRecordService;
	
	/**
	 * 统计首页
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "/index")
	public String index(HttpServletRequest request){
		VisitRecordVo visitRecordVo = visitRecordService.select();
		return ResUtils.okRes(visitRecordVo);
	}
}
