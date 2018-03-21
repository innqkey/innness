package com.huisou.weixin.handler;

import me.chanjar.weixin.common.session.WxSessionManager;
import me.chanjar.weixin.mp.api.WxMpService;
import me.chanjar.weixin.mp.bean.message.WxMpXmlMessage;
import me.chanjar.weixin.mp.bean.message.WxMpXmlOutMessage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.huisou.po.UserPo;
import com.huisou.service.UserService;

import java.util.Map;

/**
 * @author Binary Wang(https://github.com/binarywang)
 */
@Component
public class UnsubscribeHandler extends AbstractHandler {
	
	@Autowired
    private UserService userService; 
	@Override
	public WxMpXmlOutMessage handle(WxMpXmlMessage wxMessage, Map<String, Object> context, WxMpService wxMpService,
			WxSessionManager sessionManager) {
		String openId = wxMessage.getFromUser();
		this.logger.info("取消关注用户 OPENID: " + openId);
		UserPo userPo = userService.getUserByOpenId(openId);
		if (null != userPo){
			userPo.setStandby2("1");
			userService.updateOne(userPo);
		}
		// TODO 更新本地数据库为取消关注状态

		return null;
	}

}
