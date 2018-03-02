package com.huisou.weixin.handler;


import java.util.Date;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.huisou.po.UserPo;
import com.huisou.service.UserService;
import com.huisou.weixin.builder.TextBuilder;

import me.chanjar.weixin.common.exception.WxErrorException;
import me.chanjar.weixin.common.session.WxSessionManager;
import me.chanjar.weixin.mp.api.WxMpService;
import me.chanjar.weixin.mp.bean.message.WxMpXmlMessage;
import me.chanjar.weixin.mp.bean.message.WxMpXmlOutMessage;
import me.chanjar.weixin.mp.bean.result.WxMpUser;

/**
 * 当用户加入的时候需要处理的类，直接是调用该接口的
 * @author Binary Wang(https://github.com/binarywang)
 */
@Component
public class SubscribeHandler extends AbstractHandler {
 @Autowired
  private UserService userService;
  @Override
  public WxMpXmlOutMessage handle(WxMpXmlMessage wxMessage,
                                  Map<String, Object> context, WxMpService weixinService,
                                  WxSessionManager sessionManager) throws WxErrorException {

    this.logger.info("新关注用户 OPENID: " + wxMessage.getFromUser());

    // 获取微信用户基本信息
    WxMpUser userWxInfo = weixinService.getUserService()
        .userInfo(wxMessage.getFromUser(), null);

    if (userWxInfo != null && StringUtils.isNotBlank(userWxInfo.getOpenId())) {
      // TODO 可以添加关注用户到本地
    	UserPo userPo= new UserPo();
    	userPo.setCity(userWxInfo.getCity());
    	userPo.setProvince(userWxInfo.getProvince());
    	userPo.setHeadimgurl(userWxInfo.getHeadImgUrl());
    	userPo.setNickname(userWxInfo.getNickname());
    	userPo.setCreateTime(new Date());
    	userPo.setOpenid(userWxInfo.getOpenId());
    	userPo.setCountry(userWxInfo.getCountry());
    	userPo.setSex(userWxInfo.getSex());
    	String eventKey = wxMessage.getEventKey();
   	  if (StringUtils.isNotBlank(eventKey)) {
   		  eventKey = eventKey.substring(8, eventKey.length());
   		  if (!eventKey.equals(wxMessage.getOpenId())) {
   			UserPo user = userService.getUserByOpenId(eventKey);
   			if (user != null ){
   				userPo.setClassmateUserId(user.getUserId());
   			}
   		}
    	userService.addOne(userPo);
   	  }
    }

//    WxMpXmlOutMessage responseResult = null;
//    try {
//     // responseResult = handleSpecial(wxMessage);
//    } catch (Exception e) {
//      this.logger.error(e.getMessage(), e);
//    }
//
//    if (responseResult != null) {
//      return responseResult;
//    }

    try {
      return new TextBuilder().build("感谢您的关注", wxMessage, weixinService);
    } catch (Exception e) {
      this.logger.error(e.getMessage(), e);
    }

    return null;
  }

//  /**
//   * 如果是同学推荐的话，那么就生成对应的同学表的关系
//   */
//  private WxMpXmlOutMessage handleSpecial(WxMpXmlMessage wxMessage)
//	      throws Exception {
//	 
//		  
//	  }
//		  
//	    return null;
//	  }
}
