package com.huisou.controller;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.common.ResUtils;
import com.github.pagehelper.PageInfo;
import com.huisou.service.RebateRecordService;
import com.huisou.vo.PageTemp;
import com.huisou.vo.RebateRecordVo;

/** 
* @author 作者 :yuhao 
* @version 创建时间：2018年3月29日 下午4:48:28 
* 类说明 
*/
@RestController
@RequestMapping(value = "/rebateRecord")
public class RebateRecordController extends BaseController {
	
	@Autowired
	private RebateRecordService rebateRecordService;
	
	@RequestMapping(value = "/findRebateRecordByuserId")
	public String findRebateRecordByuserId(HttpServletRequest request,PageTemp pageTemp){
		try {
			String userToken = request.getParameter("userToken");
			if(StringUtils.isBlank(userToken)){
				return ResUtils.errRes("102", "请求参数错误");
			}
			Integer userId = super.getUserIdByToken(userToken);
			PageInfo<RebateRecordVo> result = rebateRecordService.findRebateRecordByuserId(userId,pageTemp);
			return ResUtils.okRes(result);
		} catch (Exception e) {
			e.printStackTrace();
			return ResUtils.execRes();
		}
	}
	
}
