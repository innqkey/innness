package com.huisou.weixin.handler;

import java.util.List;

import me.chanjar.weixin.common.exception.WxErrorException;
import me.chanjar.weixin.mp.api.WxMpService;
import me.chanjar.weixin.mp.bean.template.WxMpTemplateData;
import me.chanjar.weixin.mp.bean.template.WxMpTemplateMessage;

/**
* 类说明： 封装模板的发送类
* @author 
* @version 创建时间：2018年3月9日 上午9:23:56
* 
*/
public class TemplateHandler {
	/**
	 * 发送模板消息
	 * @param getTemplateId模板的id
	 * @param openId
	 * @param wxMpTemplateDatas根据对应的模板填充对应的消息
	 * @param url 这个是模板需要跳转url
	 * @throws WxErrorException
	 */
	public static void sendTemplateMsg(WxMpService wxService,String getTemplateId, String openId,List<WxMpTemplateData> wxMpTemplateDatas,String url) throws WxErrorException {
		// 构建消息
		WxMpTemplateMessage templateMessage = WxMpTemplateMessage.builder().toUser(openId).templateId(getTemplateId)
				.build();
		if (wxMpTemplateDatas != null && wxMpTemplateDatas.size() > 0) {
			for (WxMpTemplateData wxMpTemplateData : wxMpTemplateDatas) {
				templateMessage.addWxMpTemplateData(wxMpTemplateData);
			}
			// 发送模板消息
			templateMessage.setUrl(url);
			wxService.getTemplateMsgService().sendTemplateMsg(templateMessage);
		}
		
		
	}
}
