package com.huisou.jms.sender;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/** 
* @author 作者 :yuhao 
* @version 创建时间：2018年3月21日 下午4:17:15 
* 类说明 
*/
@Component
public class WxMpPayCourseSender {
	@Autowired
	private AmqpTemplate amqpTemplate;
	
	public final Logger logger = LoggerFactory.getLogger(WxMpPayCourseSender.class);
	public void payCourseSender(String orderNo){
		logger.info("发送订阅消息==============================="+orderNo);
		amqpTemplate.convertAndSend("exchange","topic.payCourseSender",orderNo);
	}
	
}
