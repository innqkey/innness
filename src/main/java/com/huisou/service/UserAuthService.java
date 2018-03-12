package com.huisou.service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/*
 * 1 第一步：用户同意授权，获取code
 * 2 第二步：通过code换取网页授权access_token
 * 3 第三步：刷新access_token（如果需要）
 * 4 第四步：拉取用户信息(需scope为 snsapi_userinfo)
 * 5 附：检验授权凭证（access_token）是否有效
 */
public interface UserAuthService {

	/*
	 * 用户同意授权，获取code
	 */
	public void authCode(String state,HttpServletResponse response);
	
	
	/*
	 * 通过code换取网页授权access_token
	 */
	public void getAuthOpenId(String code, String state, HttpServletRequest request, HttpServletResponse response);
	
	public String getUserinfo(String accessToken, String openId);
	
}
