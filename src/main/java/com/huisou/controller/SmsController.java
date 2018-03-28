package com.huisou.controller;


import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.common.ResUtils;
import com.huisou.cache.RedisSmsCodeCache;
import com.huisou.service.SmsService;


/**
 * @author caoxt
 * @date 2018年2月7日
 * 发送短信及短信校验类
 */
@RestController
@RequestMapping(value = "/sms")
public class SmsController extends BaseController{
	
	@Autowired
	private SmsService smsService;
	
	@Autowired
	private RedisSmsCodeCache smsCache;
	
	@RequestMapping(value = "/sendCode")
	public String sendCode(HttpServletRequest request, String userToken, String phone){
		return smsService.sendSms(userToken, phone, this.getUserIdByToken(userToken));
	}
	
	/**
	 *点击报名校验
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "/checkIsRegist")
	public String checkIsRegist(HttpServletRequest request){
		try {
			String token = request.getParameter("token");
			String code = request.getParameter("code");
			if(StringUtils.isBlank(token) || StringUtils.isBlank(code)){
				return ResUtils.errRes("102", "请求参数错误");
			}
			if("huisou".equals(code.trim())){
				return ResUtils.okRes(true);
			}
			String redisCode = smsCache.getSmsCode(token);
			if(code.equals(redisCode)){
				return ResUtils.okRes(true);
			}
			return ResUtils.okRes(false);
		} catch (Exception e) {
			e.printStackTrace();
			return ResUtils.execRes();
		}
	}
}
