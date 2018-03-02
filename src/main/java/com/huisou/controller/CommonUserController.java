package com.huisou.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.common.MD5Util;
import com.common.ResUtils;
import com.huisou.po.CommonUserPo;
import com.huisou.service.CommonUserService;
import com.huisou.vo.PageTemp;

/** 
* @author 作者 :yuhao 
* @version 创建时间：2018年2月11日 上午11:19:28 
* 类说明 
*/
@RestController
@RequestMapping(value = "/commonUser")
public class CommonUserController extends BaseController{
	
	@Autowired
	private CommonUserService commonUserService;
	
	/**
	 * 添加后台用户
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "/addAndUpdate")
	public String addAndUpdate(HttpServletRequest request){
		try {
			String commonUserId = request.getParameter("commonUserId");
			String username = request.getParameter("username");
			String password = request.getParameter("password");
			if(StringUtils.isBlank(username) || StringUtils.isBlank(password)){
				return ResUtils.errRes("102", "请求参数错误");
			}
			CommonUserPo commonUserPo = new CommonUserPo();
			commonUserPo.setUsername(username);
			commonUserPo.setPassword(MD5Util.md5Encode(MD5Util.md5Encode(password)));
			if(StringUtils.isBlank(commonUserId)){
				CommonUserPo po = commonUserService.search(username,null);
				if(null==po){
					commonUserService.add(commonUserPo);	
				}else{
					return ResUtils.errRes("106", "用户名已存在");
				}
			}else{
				commonUserPo.setCommonUserId(Integer.parseInt(commonUserId));
				commonUserService.update(commonUserPo);
			}
			return ResUtils.okRes();
		} catch (Exception e) {
			e.printStackTrace();
			return ResUtils.execRes();
		}
	}
	
	/**
	 * 查找所有的用户
	 * @param request
	 * @param pageTemp
	 * @return
	 */
	@RequestMapping(value = "/findAll")
	public String findAll(HttpServletRequest request,PageTemp pageTemp){
		try {
			List<CommonUserPo> list = commonUserService.findAll(pageTemp);
			return ResUtils.okRes(list);
		} catch (Exception e) {
			e.printStackTrace();
			return ResUtils.execRes();
		}
	}
	
	/**
	 * 登录
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "/login")
	public String login(HttpServletRequest request){
		try {
			String username = request.getParameter("username");
			String password = request.getParameter("password");
			if(StringUtils.isBlank(username) || StringUtils.isBlank(password)){
				return ResUtils.errRes("102", "请求参数错误");
			}
			CommonUserPo commonUserPo = commonUserService.search(username, MD5Util.md5Encode(MD5Util.md5Encode(password)));
			if(null == commonUserPo){
				return ResUtils.errRes("108", "账号或密码错误");
			}else{
				return ResUtils.okRes(commonUserPo);
			}
		} catch (Exception e) {
			e.printStackTrace();
			return ResUtils.execRes();
		}
	}
}
