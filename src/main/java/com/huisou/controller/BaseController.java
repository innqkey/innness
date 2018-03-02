package com.huisou.controller;

import java.util.Enumeration;
import java.util.LinkedHashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import com.huisou.cache.RedisUserTokenCache;
import com.huisou.po.UserPo;
import com.huisou.service.UserService;

@Controller
@SessionAttributes
public class BaseController {
	
	@Autowired
	private RedisUserTokenCache userTokenCache;
	
	@Autowired
	private UserService userService;
	
	public Map<String,String> getPara(){
		HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
        HttpSession session = request.getSession();
        Map map = new LinkedHashMap();
        Enumeration enu=request.getParameterNames();  
        while(enu.hasMoreElements()){  
        	String paraName=(String)enu.nextElement();
        	map.put(paraName, request.getParameter(paraName));
        }
        return map;
	}
	
	public String getOpenIdByToken(String userToken){
		return this.getUserPoByToken(userToken).getOpenid();
	}
	
	public int getUserIdByToken(String userToken){
		return this.getUserPoByToken(userToken).getUserId();
	}
	
	public UserPo getUserPoByToken(String userToken){
		return userTokenCache.getUserPoByToken(userToken);
	}
	
	public UserPo getRegistPoByToken(String userToken){
		return userService.find(this.getUserIdByToken(userToken));
	}
}
