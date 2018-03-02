package com.huisou.controller;

import java.util.Date;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.common.DateUtils;
import com.common.ResUtils;
import com.github.pagehelper.PageInfo;
import com.huisou.service.IntegralRecordService;
import com.huisou.vo.IntegralRecordVo;
import com.huisou.vo.PageTemp;

/** 
* @author 作者 :yuhao 
* @version 创建时间：2018年1月31日 下午7:16:31 
* 类说明 
*/
@RestController
@RequestMapping(value = "/integralRecord")
public class IntegralRecordController {

	@Autowired
	private IntegralRecordService integralRecordService;
	
	@RequestMapping(value = "/index")
	public String index(HttpServletRequest request,PageTemp pageTemp){
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
			PageInfo<IntegralRecordVo> result = integralRecordService.search(nickname,phone,startDate,endDate,pageTemp);
			return ResUtils.okRes(result);
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			return ResUtils.execRes();
		}
	}
}
