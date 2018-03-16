package com.huisou.weixin.controller;

import java.util.Date;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.common.ResUtils;
import com.github.binarywang.wxpay.bean.notify.WxPayNotifyResponse;
import com.github.binarywang.wxpay.bean.notify.WxPayOrderNotifyResult;
import com.github.binarywang.wxpay.bean.request.WxPayUnifiedOrderRequest;
import com.github.binarywang.wxpay.constant.WxPayConstants.SignType;
import com.github.binarywang.wxpay.constant.WxPayConstants.TradeType;
import com.github.binarywang.wxpay.exception.WxPayException;
import com.github.binarywang.wxpay.service.WxPayService;
import com.huisou.constant.ContextConstant;
import com.huisou.mapper.WeixinCallbackRecordPoMapper;
import com.huisou.po.AwardRecordPo;
import com.huisou.po.OrderPo;
import com.huisou.po.PayRecordPo;
import com.huisou.po.WeixinCallbackRecordPo;
import com.huisou.service.AwardRecordService;
import com.huisou.service.OrderService;
import com.huisou.service.PayRecordService;
import com.huisou.weixin.config.WxPayProperties;



/**
 * <pre>
 *  注意此controller类实现接口WxPayService（implements WxPayService ），
 *  仅是为了方便演示所有接口的使用，以免漏掉某一个新增加的接口，实际使用时无需如此实现。
 *  </pre>
 *
 * @author Binary Wang
 */
@RestController
@RequestMapping("/pay")
public class WxPayController   {
  private final Logger logger = LoggerFactory.getLogger(this.getClass());

  @Resource(name = "wxPayService")
  private WxPayService wxService;

  @Autowired
  private WxPayProperties wxPayProperties;
  @Autowired
  private OrderService orderService;
  @Autowired
  private PayRecordService payRecordService;
  
  
  @Value("${wechat.pay.reward.notifyUrl}")
  private String rewardNotifyUrl;
  
  @Autowired
  private AwardRecordService awardRecordService;
  @Autowired
  private WeixinCallbackRecordPoMapper weixinCallbackRecordPoMapper;
  
  
  
  /**
   * 异步获取课程支付的账户返回的系统的信息和结果
   * 并对判断订单号，用户付款金额，和支付金额等
   * 支付的总金额，等于现金金额  + 优惠券（微信支付）金额等。
   */
  @PostMapping("/getFeedBackInfo")
  public String parseOrderNotifyResult(@RequestBody String xmlData) throws WxPayException {
	  try {
	      logger.info("微信支付异步通知请求参数：{}", xmlData);
	      WxPayOrderNotifyResult result = this.wxService.parseOrderNotifyResult(xmlData);
	      if (result.getReturnCode() != null && !result.getReturnCode().equals("SUCCESS")) {
	    	  return WxPayNotifyResponse.fail(result.getReturnMsg());
	      }
	      
	      WeixinCallbackRecordPo callbackRecordPo = new WeixinCallbackRecordPo();
	      org.springframework.beans.BeanUtils.copyProperties(result, callbackRecordPo);
	      weixinCallbackRecordPoMapper.insert(callbackRecordPo);
	      
	      //用来支付是否成功
	      if (result.getResultCode().equals("SUCCESS")) {
	    	  PayRecordPo payRecordPo = new PayRecordPo();
	    	  payRecordPo.setWeixinRecord(result.getTransactionId());
	    	  payRecordPo.setCreateTime(new Date());
	    	  
	    	  //首先判断商务号是否正确
	    	  if ( wxPayProperties.getMchId().equals(result.getMchId())) {
	    		  //看看数据库中是否有该订单号
	    		  if (result.getOutTradeNo() != null) {
	    			  
	    			String outTradeNo = result.getOutTradeNo();
					OrderPo order= orderService.findByoutTradeNo(outTradeNo);
					//说明存在该订单号
					if (order != null ) {
						
						//判断金额是否正确
						Integer totalFee = result.getTotalFee();
						if (totalFee != null) {
							
							int payedValue = totalFee.intValue();
							int payOrderValue = (int) (order.getOrderPay().floatValue()) * 100;
							if (payedValue == payOrderValue) {
								
								logger.info("支付成功");
								//说明成功，更改数据库的状态，并且保存支付信息
								OrderPo orderPo = new OrderPo();
								orderPo.setOrderId(order.getOrderId());
								orderPo.setPayStatus(ContextConstant.PAY_STATUS_SUCCESS);
								orderService.update(orderPo);
								//保存对应的支付信息
								payRecordPo.setUserId(order.getUserId());
								payRecordPo.setOrderId(order.getOrderId());
								payRecordPo.setPayMoney(order.getOrderPay());
								payRecordPo.setResId(order.getResId());
								payRecordPo.setResPrice(order.getOrderPay());
								payRecordPo.setResType(order.getResType());
								payRecordPo.setPayStatus(ContextConstant.PAY_STATUS_SUCCESS);
							}else {
								logger.error("错误的金额,应付金额是" + payOrderValue + "已经支付金额是" + payedValue);
								payRecordPo.setPayFailCause(ContextConstant.PAY_FAIL_ERROR_NUM);
								payRecordPo.setUserId(order.getUserId());
								payRecordPo.setOrderId(order.getOrderId());
								payRecordPo.setPayMoney(order.getOrderPay());
								payRecordPo.setResId(order.getResId());
								payRecordPo.setResPrice(order.getOrderPay());
								payRecordPo.setResType(order.getResType());
								payRecordPo.setPayStatus(ContextConstant.PAY_STATUS_FAIL);
							}
							
						}
						
					}else {
						 logger.error("错误的订单号::" + result.getOutTradeNo());
						 payRecordPo.setPayFailCause(ContextConstant.PAY_FAIL_ERROR_ORDERID);
						 payRecordPo.setPayStatus(ContextConstant.PAY_STATUS_FAIL);
					}
					
				}else {
					 logger.error("订单号为空 :-----" );
					 payRecordPo.setPayFailCause(ContextConstant.PAY_FAIL_ERROR_NULLODERID);
					 payRecordPo.setPayStatus(ContextConstant.PAY_STATUS_FAIL);
				}
	    	  }else {
	    		  //如果商务号不对的话，那么记录用户的对应的信息
	    		  logger.error("错误的商务号用户" + result.getMchId());
	    		  payRecordPo.setPayFailCause(ContextConstant.PAY_FAIL_ERROR_ACCOUNT);
	    		  payRecordPo.setPayStatus(ContextConstant.PAY_STATUS_FAIL);
	    	  }
	    	  
	    	  payRecordService.add(payRecordPo);
	      }
	      
	      return WxPayNotifyResponse.success("OK");
	    } catch (WxPayException e) {
	    	logger.error(e.getMessage(), e);
	      throw e;
	    } catch (Exception e) {
	    	logger.error(e.getMessage(), e);
	      throw new WxPayException("发生异常，" + e.getMessage(), e);
	    }
  }
  
  /**
   * 这个是打赏的会调用的地址
   * 支付的总金额，等于现金金额  + 优惠券（微信支付）金额等。
   */
  @PostMapping("/getRewardFeedBackInfo")
  public String getRewardFeedBackInfo(@RequestBody String xmlData) throws WxPayException {
	  try {
		  logger.info("微信支付异步通知请求参数：{}", xmlData);
		  WxPayOrderNotifyResult result = this.wxService.parseOrderNotifyResult(xmlData);
		  if (result.getReturnCode() != null && !result.getReturnCode().equals("SUCCESS")) {
	    	  return WxPayNotifyResponse.fail(result.getReturnMsg());
	      }
		  
		  WeixinCallbackRecordPo callbackRecordPo = new WeixinCallbackRecordPo();
		  BeanUtils.copyProperties(result, callbackRecordPo);
	      weixinCallbackRecordPoMapper.insert(callbackRecordPo);
	      
		  //用来检查是否成功，
		  if (result.getResultCode().equals("SUCCESS")) {
			  //首先判断商务号是否正确
			  if ( wxPayProperties.getMchId().equals(result.getMchId())) {
				  //看看数据库中是否有该订单号
				  if (result.getOutTradeNo() != null) {
					  //获取打赏
					  AwardRecordPo awardRecordPo = awardRecordService.findOne(result.getOutTradeNo());
					  //说明存在该订单号
					  if (awardRecordPo != null) {
						  //判断金额是否正确
						  Integer totalFee = result.getTotalFee();
						  if (totalFee != null) {
							  int payedValue = totalFee.intValue();
							  int payOrder = (int) (awardRecordPo.getAwardMoney().floatValue()) * 100;
							  if (payedValue == payOrder) {
								  logger.info("打赏成功" + result);
								  //说明成功，更改数据库的状态，并且保存支付信息
								  awardRecordPo.setAwardStatus(ContextConstant.PAY_STATUS_SUCCESS);
								  awardRecordService.update(awardRecordPo);
							  }
						  }
						  
					  }else {
						  logger.error("错误的订单号" );
					  }
				  }else {
					  logger.error("订单为空");
				  }
				  
			  }else {
				  //如果商务号不对的话，那么记录用户的对应的信息
				  logger.error("错误的商务号用户" );
			  }
			  
		  }
		  
		  return WxPayNotifyResponse.success("ok");
	  } catch (WxPayException e) {
		  logger.error(e.getMessage(), e);
		  throw e;
	  } catch (Exception e) {
		  logger.error(e.getMessage(), e);
		  throw new WxPayException("发生异常，" + e.getMessage(), e);
	  }
  }
  
  /**
   * 首先 选哟使用的
   * appid，Mchid，key，signType都有默认的或者配置了
   * 只有body,openid,totalFee,和outTradeNo需要前台传过来
   * 创建用户的订单和微信直接连接，发送支付请求
   * 
   */
  @RequestMapping(value = "/createOrder",method =RequestMethod.GET)
  public String createOrder( WxPayUnifiedOrderRequest request,HttpServletRequest httpServletRequest) throws WxPayException {
	 //设置请不求类型为公众号
	try {
		logger.info("音频课程等创建订单的时候接受参数: {}", request);
		if (StringUtils.isBlank(request.getBody()) || StringUtils.isBlank(request.getOpenid()) 
				|| request.getTotalFee() == null || StringUtils.isBlank(request.getOutTradeNo())) {
			return ResUtils.execRes("参数不能为空");
		}
		
		
		Integer totalFee = request.getTotalFee();
		request.setTotalFee(totalFee * 100);
		request.setTradeType(TradeType.JSAPI);
		//生成随机字符串
		request.setNonceStr(String.valueOf(System.currentTimeMillis() / 1000));
		//终端的本地的发送的本地的ip地址
		request.setSpbillCreateIp(httpServletRequest.getRemoteAddr());
		//设置签名的类型，默认是md5进行签名
		request.setSignType(SignType.MD5);
		logger.info("音频课程等处理后发送微信服务器: {}", request);
		Object createOrder = this.wxService.createOrder(request);
		
		return ResUtils.okRes(createOrder);
	} catch (Exception e) {
		
		e.printStackTrace();
	}
	
	return ResUtils.execRes();
  }
  
  
  /**
   * 首先 选哟使用的
   * appid，Mchid，key，signType都有默认的或者配置了
   * 只有body,openid,totalFee,和outTradeNo需要前台传过来
   * 创建用户的订单和微信直接连接，发送支付请求
   * 
   */
  @RequestMapping(value = "/createRewardOrder",method =RequestMethod.GET)
  public String createRewardOrder( WxPayUnifiedOrderRequest request,HttpServletRequest httpServletRequest) throws WxPayException {
	 //设置请不求类型为公众号
	try {
		logger.info("打赏接受的参数: {}", request);
		if (StringUtils.isBlank(request.getBody()) || StringUtils.isBlank(request.getOpenid()) 
				|| request.getTotalFee() == null || StringUtils.isBlank(request.getOutTradeNo())) {
			return ResUtils.execRes("参数不能为空");
		}
		
		//修改打赏的回掉地址
		request.setNotifyURL(rewardNotifyUrl);
		Integer totalFee = request.getTotalFee();
		request.setTotalFee(totalFee * 100);
		request.setTradeType(TradeType.JSAPI);
		//生成随机字符串
		request.setNonceStr(String.valueOf(System.currentTimeMillis() / 1000));
		//终端的本地的发送的本地的ip地址
		request.setSpbillCreateIp(httpServletRequest.getRemoteAddr());
		//设置签名的类型，默认是md5进行签名
		request.setSignType(SignType.MD5);
		logger.info("打赏发送微信的参数: {}", request);
		Object createOrder = this.wxService.createOrder(request);
		
		return ResUtils.okRes(createOrder);
	} catch (Exception e) {
		e.printStackTrace();
	}
	
	return ResUtils.execRes();
  }
  

}

