package com.huisou.controller;


import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.common.ResUtils;
import com.github.pagehelper.PageInfo;
import com.huisou.po.NotificationPo;
import com.huisou.service.NotificationService;
import com.huisou.vo.PageTemp;

/** 
* @author 作者 :yuhao 
* @version 创建时间：2018年3月8日 下午2:28:55 
* 类说明 
*/
@RestController
@RequestMapping(value = "/notification")
public class NotificationController extends BaseController{
	
	@Autowired
	private NotificationService notificationService;
	
	@RequestMapping(value = "/findAll")
	public String findAll(HttpServletRequest request,PageTemp pageTemp){
		try {
			String userToken = request.getParameter("userToken");
			if(StringUtils.isBlank(userToken)){
				return ResUtils.errRes("102", "请求参数错误");
			}
			PageInfo<NotificationPo> result = notificationService.findAll(super.getUserIdByToken(userToken),pageTemp);
			return ResUtils.okRes(result);
		} catch (Exception e) {
			e.printStackTrace();
			return ResUtils.execRes();
		}
	}
}
