package com.huisou.controller;

import java.util.Date;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.common.ResUtils;
import com.huisou.po.RotationImagePo;
import com.huisou.service.RotationImageService;

/** 
* @author qinkai 
* @date 2018年3月13日
*/

@RestController
@RequestMapping(value = "/wechatShare")
public class WechatShareController {
	
	@Autowired
	private RotationImageService rotationImageService;

	@RequestMapping(value = "/addAndUpdate",method = RequestMethod.POST)
	public String addAndUpdate(String shareUrlName, String shareUrlContent, String shareUrlLogo){
		if (StringUtils.isBlank(shareUrlName) || StringUtils.isBlank(shareUrlContent) || 
				StringUtils.isBlank(shareUrlLogo)){
			return ResUtils.errRes("404", "微信分享的链接地址为空");
		} else {
			rotationImageService.deleteImageByType("3");
			RotationImagePo imagePo = new RotationImagePo();
			imagePo.setNoticeCertificateId(0);
			imagePo.setNoticeCertificateContent(shareUrlContent);
			imagePo.setRotationImageName(shareUrlName);
			imagePo.setLinkUrlType("3");
			imagePo.setLinkUrl(shareUrlLogo);
			imagePo.setCreateTime(new Date());
			rotationImageService.add(imagePo);
			return ResUtils.okRes();
		}
	}
	
	@RequestMapping(value = "/detail")
	public String detail(){
		RotationImagePo imagePo = rotationImageService.findOne("3");
		return ResUtils.okRes(imagePo);
	}
}
