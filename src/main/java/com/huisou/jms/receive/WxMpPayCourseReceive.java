package com.huisou.jms.receive;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import com.common.JacksonUtil;
import com.huisou.po.OrderPo;
import com.huisou.po.UserPo;
import com.huisou.service.CourseService;
import com.huisou.service.OrderService;
import com.huisou.service.RebateRecordService;
import com.huisou.service.UserService;
import com.huisou.vo.CourseVo;
import com.huisou.weixin.handler.TemplateHandler;

import me.chanjar.weixin.mp.api.WxMpService;
import me.chanjar.weixin.mp.bean.template.WxMpTemplateData;

/** 
* @author 作者 :yuhao 
* @version 创建时间：2018年3月21日 下午4:28:04 
* 类说明 
*/
@Component
public class WxMpPayCourseReceive {
	
	public final Logger logger = LoggerFactory.getLogger(this.getClass());
	
	@Autowired
	private AmqpTemplate amqpTemplate;
	
	@Autowired
	private UserService userService;
	
	@Autowired
	private WxMpService weixinService;
	
	@Autowired
	private CourseService courseService;
	
	@Autowired
	private RebateRecordService rebSer;
	
	@Autowired
	private OrderService orderService;
	
	@Value("${success.url}")
	private String successurl;
	
	@Value("${templateId.payCourseUser}")
	private String payCourseUserId;
	
	@Value("${templateId.payCourseAgent}")
	private String payCourseAgentId;
	
	@RabbitListener(queues="topic.payCourseSender1")
	public void WxMpUser(String orderNo){
		try {
			logger.info("进入WxMpUser，发送模版推送给客户自己");
		OrderPo orderPo = orderService.findByoutTradeNo(orderNo);
		if(!"KC".equals(orderPo.getResType())){
			return;
		}
		UserPo userPo = userService.find(orderPo.getUserId());
		CourseVo courseVo = courseService.findOne(orderPo.getResId());
		//发送模板消息
		ArrayList<WxMpTemplateData> templateDataList = new ArrayList<WxMpTemplateData>();
		SimpleDateFormat dateFormat = new SimpleDateFormat(
     		      "yyyy-MM-dd HH:mm:ss.SSS");
		templateDataList.add(new WxMpTemplateData("first", "恭喜您已经成功报名《"+courseVo.getCourseTitle()+"课程》，以下是您的课程安排："));
		templateDataList.add(new WxMpTemplateData("keyword1",userPo.getUsername()));
		templateDataList.add(new WxMpTemplateData("keyword2",courseVo.getCourseTitle()));
		templateDataList.add(new WxMpTemplateData("remark", "点击此消息申请相应的场次，选择对应的时间、地点"));
		TemplateHandler.sendTemplateMsg(weixinService,payCourseUserId, userPo.getOpenid(), templateDataList, successurl);
		} catch (Exception e) {
			logger.error("支付成功发送模版推送给客户自己错误");
			e.printStackTrace();
		}
	}
	
	@RabbitListener(queues="topic.payCourseSender2")
	public void WxMpAgent(String orderNo){
		try {
			logger.info("进入WxMpUser，发送模版推送给分享人");
			OrderPo orderPo = orderService.findByoutTradeNo(orderNo);
			if(!"KC".equals(orderPo.getResType())){
				return;
			}
			UserPo userPo = userService.find(orderPo.getUserId());
			UserPo classmateUser = userService.find(userPo.getClassmateUserId());
			if(classmateUser!=null){
				logger.info("分享人存在，不为空");
				CourseVo courseVo = courseService.findOne(orderPo.getResId());
				//发送模板消息
				ArrayList<WxMpTemplateData> templateDataList = new ArrayList<WxMpTemplateData>();
				SimpleDateFormat dateFormat = new SimpleDateFormat(
		     		      "yyyy-MM-dd HH:mm:ss.SSS");
				templateDataList.add(new WxMpTemplateData("first", "您好，有用户通过您的推荐成功报名课程："));
				templateDataList.add(new WxMpTemplateData("keyword1",userPo.getNickname()));
				templateDataList.add(new WxMpTemplateData("keyword2", dateFormat.format(new Date())));
				templateDataList.add(new WxMpTemplateData("remark", "点击此消息查看您的收益"));
				TemplateHandler.sendTemplateMsg(weixinService,payCourseAgentId, classmateUser.getOpenid(), templateDataList, successurl);
			}
		} catch (Exception e) {
			logger.error("支付成功发送模版推送给分享人错误");
			e.printStackTrace();
		}
	}
	
	/*
	 * 订阅订单支付事件
	 */
	@RabbitListener(queues="topic.payCourseSender3")
	public void rebate(String orderNo){
		try {
			logger.info("进入rebate订阅事件，返现====");
			OrderPo orderPo = orderService.findByoutTradeNo(orderNo);
			rebSer.rebate(orderPo);
		} catch (Exception e) {
			logger.error("进入rebate订阅事件，返现====错误");
			e.printStackTrace();
		}
	}
}
