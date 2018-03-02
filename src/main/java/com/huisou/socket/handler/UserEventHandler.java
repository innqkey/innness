package com.huisou.socket.handler;

import java.util.Date;
import java.util.concurrent.ConcurrentHashMap;
import java.util.regex.Pattern;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.corundumstudio.socketio.AckRequest;
import com.corundumstudio.socketio.SocketIOClient;
import com.corundumstudio.socketio.annotation.OnConnect;
import com.corundumstudio.socketio.annotation.OnDisconnect;
import com.corundumstudio.socketio.annotation.OnEvent;
import com.huisou.constant.ContextConstant;
import com.huisou.mapper.ChatMessagePoMapper;
import com.huisou.po.ChatHistoryPo;
import com.huisou.po.ChatMessagePo;
import com.huisou.service.ChatHistoryService;
import com.huisou.service.ChatMessageService;
import com.huisou.socket.client.NettyClients;
import com.huisou.vo.ParamIds;



/**
 * 目前展示不用
 * 
 * 
 * 
 * 客服的处理器，客服是以个特殊的userId构成的客服系统
 * 功能描述：
 * @Package: com.huisou.socket.handler 
 * @author: Administrator   
 * @date: 2018年2月27日 上午11:47:40
 */
//@Component
public class UserEventHandler {  
	
	private final Logger logger = LoggerFactory.getLogger(UserEventHandler.class);
	
	private final  ConcurrentHashMap<String, String> tempMap = new ConcurrentHashMap<>();
	//正则表达式
	private Pattern pattern =  Pattern.compile(ContextConstant.URL_PATTERN);
	
	@Autowired
	private ChatHistoryService keFuHistoryService;
	@Autowired
	private ChatMessageService keFuMessageService;
	
    /**
     * 当用户连接的时候保存用户的socket等
     * 相关的信息
     * @param client
     */
    @OnConnect  
    public void onConnect(SocketIOClient client){
    	client.sendEvent("connect", "connected");
    }  
      
    /*
     * 用户接入，同时分配给客服
     */
    @OnEvent(value = "userJoin")  
    public void userJoin(SocketIOClient client, AckRequest request, ParamIds ids){
    	String userId = ids.getUserId();
    	//参数为空断开连接
    	if (StringUtils.isBlank(userId)) {
    		client.sendEvent("errPara", "102");
    		client.disconnect();
    		return;
    	}
    	tempMap.putIfAbsent(client.getSessionId().toString(), userId);
    	NettyClients.getInstance().putUserEventClient(userId, client);
    }
    /**
     * 
     * 什么时候离线，就是在用用户关闭窗口的时候，删除Netty中的对象
     * @param client
     */
    @OnDisconnect  
    public void onDisconnect(SocketIOClient client){
    	String userId = tempMap.get(client.getSessionId().toString());
    	if (StringUtils.isNotBlank(userId)) {
	    	//删除在线的客服,根据client的sessionId 删除
	    	NettyClients.getInstance().removeUserEventClient(userId);
	    	System.out.println("退出的session的id==" + client.getSessionId().toString() );
	    	tempMap.remove(client.getSessionId().toString());
    	}
    }  
      
    /**
     * 发送消息
     * @param client
     * @param request
     * @param data
     */
    @OnEvent(value = "message")
    public void onEvent(SocketIOClient client, AckRequest request, ChatMessagePo data){
    	
    	//判断消息是否为空
    	if (data != null && StringUtils.isNotBlank(data.getMessage()) && data.getToUser() != null) {
    		
    		logger.info("客服发送消息--FromuserId=="+data.getFromUser()+";custId=="+data.getToUser()+";message=="+data.getMessage());
    		
    		data.setMessage(data.getMessage().trim());
    	 	if (StringUtils.isBlank(data.getMsgtype())) {
    			data.setMsgtype(ContextConstant.MES_TEXT);
    		}
        	//对消息的长度进行限制
        	if(!StringUtils.isBlank(data.getMessage()) && data.getMessage().length() > 500){
        		data.setMessage(data.getMessage().trim().substring(0 , 500));
        	}
        	//如果消息发送含有http或者https话
          	if(ContextConstant.MES_TEXT.equals(data.getMsgtype()) && pattern.matcher(data.getMessage().trim()).matches()){
        		data.setMessage("<a style='color:blue'  href='" + data.getMessage() + "' target='view_window'>" + data.getMessage() + "</a>");
        	}
    	
          	//保存历史的消息
          	
          	
          	ChatHistoryPo kefuHistoryPo = new ChatHistoryPo();
          	kefuHistoryPo.setCreateTime(new Date());
          	kefuHistoryPo.setFromUser(data.getFromUser());
          	kefuHistoryPo.setToUser(data.getToUser());
          	kefuHistoryPo.setStatus(ContextConstant.HISTORY_EXIT);
          	kefuHistoryPo.setCreateTime(new Date());
          	keFuHistoryService.insertHistory(kefuHistoryPo);
          	
          	data.setCreatetime(new Date());
          	//保存历史的消息
          	keFuMessageService.save(data);
          	
          	
          	//发送消息
          	NettyClients.getInstance().sendUserEventMessage(data.getToUser()+"", "message", data);

    	}
    
    }
    
  
   
}  