package com.huisou.controller;

import java.util.Date;
import java.util.Random;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.common.DateUtils;
import com.common.ResUtils;
import com.huisou.cache.RedisSmsCodeCache;
import com.huisou.constant.ContextConstant;
import com.huisou.po.RefundPo;
import com.huisou.service.RefundService;
import com.huisou.service.SmsService;
import com.huisou.service.UserSetService;

/**
* 类说明：退款控制的controller
* @author 
* @version 创建时间：2018年3月15日 下午2:11:45
* 
*/
@RequestMapping("/refund")
@RestController
public class RefundController {
	
	@Autowired
	private SmsService smsService;
	@Autowired
	private RefundService refundService;
	@Autowired
	private RedisSmsCodeCache smsCodeCache;
	@Autowired
	private UserSetService userSetService;
	
	
	
	/**
	 * 发送短信
	 * @param phone
	 * @return
	 */
	@RequestMapping("/sendSms")
	public String sendSms(String phone) {
		if (StringUtils.isBlank(phone)) {
			return ResUtils.execRes("手机号不能为空");
		}
		
		try {
			phone = phone.trim();
			//对手机号进行判断
			Boolean isOrNosend = userSetService.findByPhone(phone);
			if (isOrNosend) {
				smsService.sendSms(phone);
			}else {
				return ResUtils.execRes("手机号码不允许");
			}
			
			
		} catch (Exception e) {
			e.printStackTrace();
			return ResUtils.execRes(e);
		}
		return ResUtils.okRes();
	}
	
	/**
	 * 验证手机短信的验证码
	 * @param code  :验证码
	 * @param phone ：手机号
	 * @return
	 */
	@RequestMapping("/validateMsg")
	public String validateMsg(String code,String phone) {
		if (StringUtils.isBlank(code) || code.length() > 4 || StringUtils.isBlank(phone)) {
			return ResUtils.execRes("验证码出错");
		}
		
		String smsCode = smsCodeCache.getSmsCode(phone.trim());
		//验证成功的话，生成对应的退款订单
		if (code.equals(smsCode)) {
			return ResUtils.okRes();
		}
		return ResUtils.execRes();
	}
	
	/**
	 * 先生成订单，然后返回订单的编号
	 * @param refundPo
	 * @return
	 */
	@RequestMapping("/createRefund")
	public String createRefund(Long returnAmount,Integer userId,Integer orderId) {
		if (returnAmount == null || userId == null || orderId == null) {
			return ResUtils.execRes("参数不为空");
		}
		
		RefundPo refundPo = new RefundPo();
		refundPo.setReturnAmount(returnAmount);
		refundPo.setUserId(userId);
		refundPo.setOrderId(orderId);
		refundPo.setCreateTime(new Date());
		refundPo.setRefundStatus(ContextConstant.REFUND_APPLY_CREATE);
		Random random = new Random();
		//设置退款的单号re + 时间戳+ orderId4位 +3位 
		String returnNo = "re" + DateUtils.getShortYMDHMS() + random.nextInt(1000);
		refundPo.setRefundNo(returnNo);
		refundService.saveRefund(refundPo);
		return ResUtils.okRes(refundPo);
	}
	
	
	
	
	

}
