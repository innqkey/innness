package com.huisou.controller;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.huisou.service.UserAuthService;

@RestController
@RequestMapping(value = "/userAuth")
public class UserAuthController extends BaseController{

	@Autowired
	private UserAuthService authService;
	
	@RequestMapping(value = "/getAuthOpenId")
	public void getAuthOpenId(HttpServletRequest request,HttpServletResponse response){
		Map<String,String> para = this.getPara();
		if(!para.containsKey("code")||StringUtils.isBlank(para.get("code"))){
			String state = request.getParameter("state");
			authService.authCode(state,response);
		}else{
			String code = para.get("code");
			String state = para.get("state");
			authService.getAuthOpenId(code, state, response);
		}
		
//		return ResUtils.okRes();
	}
}
