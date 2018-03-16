package com.huisou.weixin.controller;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.common.ResUtils;

import me.chanjar.weixin.common.bean.WxJsapiSignature;
import me.chanjar.weixin.common.exception.WxErrorException;
import me.chanjar.weixin.mp.api.WxMpService;

/**
* 类说明：
* @author 
* @version 创建时间：2018年3月9日 下午3:06:21
* 
*/
@RequestMapping("/wechat/js")
@RestController
public class WeChatJSApiController {
	@Autowired
	private WxMpService wxService;
	
	@RequestMapping("/getApi")
	public String getApi(String url) {
		WxJsapiSignature signature;
		try {
			if (StringUtils.isBlank(url)) {
				return ResUtils.execRes("路径不能为空");
			}
			signature = this.wxService.createJsapiSignature(url);
		} catch (WxErrorException e) {
			e.printStackTrace();
			return ResUtils.execRes();
		}
		return ResUtils.okRes(signature);
	}
	
}
