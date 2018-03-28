package com.huisou.weixin.handler;


import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Map;
import java.util.regex.Matcher;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import com.huisou.constant.ContextConstant;
import com.huisou.po.IntegralRecordPo;
import com.huisou.po.NotificationPo;
import com.huisou.po.UserPo;
import com.huisou.service.IntegralRecordService;
import com.huisou.service.NotificationService;
import com.huisou.service.UserAuthService;
import com.huisou.service.UserService;
import com.huisou.weixin.builder.TextBuilder;

import me.chanjar.weixin.common.exception.WxErrorException;
import me.chanjar.weixin.common.session.WxSessionManager;
import me.chanjar.weixin.mp.api.WxMpService;
import me.chanjar.weixin.mp.bean.message.WxMpXmlMessage;
import me.chanjar.weixin.mp.bean.message.WxMpXmlOutMessage;
import me.chanjar.weixin.mp.bean.result.WxMpUser;
import me.chanjar.weixin.mp.bean.template.WxMpTemplateData;

/**
 * 当用户加入的时候需要处理的类，直接是调用该接口的
 * @author Binary Wang(https://github.com/binarywang)
 */
@Component
public class SubscribeHandler extends AbstractHandler {
	@Autowired
    private UserService userService;
    @Autowired
    private NotificationService notificationService;
    @Autowired
    private IntegralRecordService integralRecordService;
    
	@Value("${templateId.classmate}")
	private String classTemplateId;
	@Value("${success.url}")
	private String successurl;
	@Autowired
    private UserAuthService authSer;
    
    @Override
    public WxMpXmlOutMessage handle(WxMpXmlMessage wxMessage,
                                  Map<String, Object> context, WxMpService weixinService,
                                  WxSessionManager sessionManager) throws WxErrorException {

    this.logger.info("新关注用户 OPENID: " + wxMessage.getFromUser());

    // 获取微信用户基本信息
    WxMpUser userWxInfo = weixinService.getUserService()
        .userInfo(wxMessage.getFromUser(), null);
    Integer classmateUserId = 0;
    Integer integralRecordId = 0;
    if (userWxInfo != null && StringUtils.isNotBlank(userWxInfo.getOpenId())){
    	UserPo userPo = userService.getUserByOpenId(userWxInfo.getOpenId());
    	if (null != userPo){
    		//已有用户重新关注
    		userPo.setCity(userWxInfo.getCity());
    		userPo.setStandby2("2");
    		userPo.setProvince(userWxInfo.getProvince());
    		userPo.setHeadimgurl(userWxInfo.getHeadImgUrl());
    		userPo.setNickname(userWxInfo.getNickname());
//    		userPo.setCreateTime(new Date());
//    		newUserPo.setOpenid(userWxInfo.getOpenId());
    		userPo.setCountry(userWxInfo.getCountry());
    		userPo.setSex(userWxInfo.getSex());
    		userService.updateOne(userPo);
    	} else {
    		//新关注的用户 可以添加关注用户到本地
    		UserPo newUserPo= new UserPo();
    		newUserPo.setStandby2("2");
    		newUserPo.setCity(userWxInfo.getCity());
    		newUserPo.setProvince(userWxInfo.getProvince());
    		newUserPo.setHeadimgurl(userWxInfo.getHeadImgUrl());
    		newUserPo.setNickname(userWxInfo.getNickname());
    		newUserPo.setCreateTime(new Date());
    		newUserPo.setOpenid(userWxInfo.getOpenId());
    		newUserPo.setCountry(userWxInfo.getCountry());
    		newUserPo.setSex(userWxInfo.getSex());
    		
    		
        	String eventKey = wxMessage.getEventKey();
	       	if (StringUtils.isNotBlank(eventKey)) {
	       		eventKey = eventKey.substring(8, eventKey.length());
	       		//新用户是否是通过别人分享关注的
	       		if (!eventKey.equals(wxMessage.getOpenId())) {
	       			UserPo user = userService.getUserByOpenId(eventKey);
	       			if (user != null ){
	       				user.setIntegralNum(user.getIntegralNum() + 100);
	       				userService.updateOne(user);
	       				classmateUserId = user.getUserId();
		       			
		       			NotificationPo notificationPo = new NotificationPo();
		       			notificationPo.setCreateTime(new Date());
		       			notificationPo.setUserId(user.getUserId());
		       			
		       			String infoNickname = userWxInfo.getNickname();
		       			Matcher matcher = ContextConstant.EMOJI.matcher(infoNickname);    
		       	        infoNickname = matcher.replaceAll("");  
		       	        
		       			notificationPo.setNotificationContext("邀请用户" + infoNickname + "增加100积分");
		       			notificationPo.setOpenId(user.getOpenid());
		       			notificationPo.setNotificationType("FX");
		       			
//		       			queueSender.addNotificationSender(notificationPo);
		       			notificationService.addOne(notificationPo);
		       			
		       			//发送模板消息
		       			ArrayList<WxMpTemplateData> templateDataList = new ArrayList<WxMpTemplateData>();
		       			SimpleDateFormat dateFormat = new SimpleDateFormat(
		       		      "yyyy-MM-dd HH:mm:ss.SSS");
		       			templateDataList.add(new WxMpTemplateData("first", "您好,您推荐新的同学进入会搜商学院"));
		       			templateDataList.add(new WxMpTemplateData("keyword1",userWxInfo.getNickname() ));
		       			templateDataList.add(new WxMpTemplateData("keyword2", dateFormat.format(new Date())));
		       			templateDataList.add(new WxMpTemplateData("remark", "感谢您的支持"));
		       			TemplateHandler.sendTemplateMsg(weixinService,classTemplateId, eventKey, templateDataList, successurl);
		       			
		       			IntegralRecordPo integralRecordPo = new IntegralRecordPo();
		       			integralRecordPo.setResPrice(100L);
		       			integralRecordPo.setUserId(user.getUserId());
		       			integralRecordPo.setCreateTime(new Date());
		       			integralRecordPo.setResType("3");
	       				integralRecordService.insertIntegralRecord(integralRecordPo);
	       				integralRecordId = integralRecordPo.getIntegralRecordId();
	       				
	       			}
	       			
	       		}
	       	}
	       	if (classmateUserId != 0){
	       		newUserPo.setClassmateUserId(classmateUserId);
	       	}
   			userService.addOne(newUserPo);
   			
   			UserPo shareUser = null;
	    	if(StringUtils.isNotBlank(eventKey)&&!"null".equals(eventKey)){
	    		logger.info("关注事件设置我的分享人====="+eventKey);
	    		shareUser = userService.getUserByOpenId(eventKey);
	    		authSer.shareUserAgent(newUserPo.getUserId(), shareUser);
	    	}
	    	
   			
   			if (integralRecordId != 0){
   				integralRecordService.updateIntegralRecord(integralRecordId,newUserPo.getUserId());
   			}
	       	NotificationPo newPo = new NotificationPo();
	       	newPo.setCreateTime(new Date());
	       	newPo.setUserId(newUserPo.getUserId());
	       	String infoNickname = userWxInfo.getNickname();
   			Matcher matcher = ContextConstant.EMOJI.matcher(infoNickname);    
   	        infoNickname = matcher.replaceAll("");
	       	newPo.setNotificationContext("亲爱的" + infoNickname + "，" + ContextConstant.ATTENTION);
	       	newPo.setOpenId(newUserPo.getOpenid());
	       	newPo.setNotificationType("GZ");
	       	//queueSender.addNotificationSender(newPo);
	       	notificationService.addOne(newPo);
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

//    try {
//    	
//      return new TextBuilder().build("", wxMessage, weixinService);
//      
//    } catch (Exception e) {
//      this.logger.error(e.getMessage(), e);
//    }

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
