package com.huisou.controller;

import java.io.IOException;
import java.util.Date;
import java.util.Enumeration;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import com.common.JacksonUtil;
import com.huisou.cache.RedisUserTokenCache;
import com.huisou.po.ResRebateSetPo;
import com.huisou.po.UserPo;
import com.huisou.service.ResRebateSetService;
import com.huisou.service.UserService;

@Controller
@SessionAttributes
public class BaseController {
	
	@Autowired
	private RedisUserTokenCache userTokenCache;
	
	@Autowired
	private UserService userService;
	
	@Autowired
	private ResRebateSetService resRebateSetService;
	
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
	/**
	 * 为每个资源添加和修改的时候设置会员返现
	 * @param request
	 * @param resId
	 * @param resType
	 */
	public void addAndUpdate(HttpServletRequest request,Integer resId,String  resType){
		String resRebates = request.getParameter("resRebates");
		String isMoney = request.getParameter("isMoney");
		if(StringUtils.isNotBlank(resRebates) && StringUtils.isNotBlank(isMoney)){
			List<ResRebateSetPo> list;
			try {
				list = JacksonUtil.toListObject(resRebates, ResRebateSetPo.class);
				for (ResRebateSetPo resRebateSetPo : list) {
					resRebateSetPo.setResId(resId);
					resRebateSetPo.setResType(resType);
					resRebateSetPo.setStandby1(isMoney);
					if(null==resRebateSetPo.getResRebateId()){
						resRebateSetPo.setCreateTime(new Date());
						resRebateSetService.addOne(resRebateSetPo);
					}else{
						resRebateSetPo.setUpdateTime(new Date());
						resRebateSetService.updateOne(resRebateSetPo);
					}
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		
	}
}
