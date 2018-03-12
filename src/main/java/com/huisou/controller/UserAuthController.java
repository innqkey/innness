package com.huisou.controller;

import java.io.IOException;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.huisou.service.UserAuthService;

@RestController
@RequestMapping(value = "/userAuth")
public class UserAuthController extends BaseController{

	private  static  final Logger logger = LoggerFactory.getLogger(UserAuthController.class);
	
	@Autowired
	private UserAuthService authService;
	
	@Value(value="${state.url}")
	private String stateUrl;
	
	@RequestMapping(value = "/getAuthOpenId")
	public void getAuthOpenId(HttpServletRequest request,HttpServletResponse response){
		Map<String,String> para = this.getPara();
		String state = request.getParameter("state");
		
		if(StringUtils.isBlank(state)){
			state = "index";
		}
		
		String userToken = String.valueOf(null!=request.getSession().getAttribute("userToken")?request.getSession().getAttribute("userToken"):"");
		if(StringUtils.isNotBlank(userToken)){
			logger.info("session----userToken----"+userToken);
			//跳转项目连接，链接传userToken参数
			try {
				logger.info("session存在userToken----跳转首页连接----");
				response.sendRedirect(stateUrl+userToken+"&state="+state);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			return;
		}
			
		if(!para.containsKey("code")||StringUtils.isBlank(para.get("code"))){
			authService.authCode(state,response);
		}else{
			String code = para.get("code");
			authService.getAuthOpenId(code, state, request, response);
		}
		
//		return ResUtils.okRes();
	}
}
