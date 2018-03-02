package com.huisou.service.impl;

import java.io.UnsupportedEncodingException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.common.MD5Util;
import com.common.ResUtils;
import com.huisou.cache.RedisSmsCodeCache;
import com.huisou.mapper.SmsPoMapper;
import com.huisou.po.SmsPo;
import com.huisou.service.SmsService;

import sun.misc.BASE64Encoder;

@Service
public class SmsServiceImpl implements SmsService {

	@Autowired
	private RedisSmsCodeCache smsCache;
	
	@Autowired
	private RestTemplate restTemplate;
	
	public static String baseUrl = "https://app.cloopen.com:8883";
	public static String appId = "8aaf07086150ec5001616a204b0c07ad";
	public static String accountSid = "8a48b5514f4fc588014f67a8f5182ea2";
	public static String authToken = "9e4008a1f862450fa9bb4b09a7693465";
	public static SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmss");
	public static String timeStr = sdf.format(new Date());
	public static String interUrl = "/2013-12-26/Accounts/"+accountSid+"/SMS/TemplateSMS?sig="+
			MD5Util.md5Encode(accountSid+authToken+timeStr);
	
	private  static  final Logger logger = LoggerFactory.getLogger(SmsServiceImpl.class);
	@Autowired
	private SmsPoMapper smsPoMapper;
	@Override
	public String sendSms(String token, String phone, Integer userId) {
		
		int num = smsPoMapper.curDateNumByUserId(userId, new SimpleDateFormat("yyyy-MM-dd").format(new Date()));
		logger.info("用户当天发送短信次数--"+userId+";"+phone+";"+num);
		if(num>20){
			return ResUtils.errRes("104", "当天发送短信不能超过20条");
		}
		
		String code = String.valueOf((int)(Math.random() * 9000 + 1000));
		logger.info("短信验证码--"+token+";"+code);
		smsCache.addSmsCode(token, code);
		
		HashMap reqMap = new HashMap(); 
		reqMap.put("to", phone);
		reqMap.put("appId", appId);
		reqMap.put("templateId", "1");
		//短信有效时间3分钟
		String smsMins = "3";
		String[] params = {code,smsMins};
		reqMap.put("datas", params);
		String jsonMap = JSON.toJSONString(reqMap);
		
		String codeUrl = baseUrl+interUrl;
		HttpHeaders headers = new HttpHeaders();
		BASE64Encoder encoder = new BASE64Encoder();
		String encodedText;
		try {
			encodedText = encoder.encode((accountSid+":"+timeStr).getBytes("UTF-8"));
			headers.add("Authorization", encodedText);
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			logger.info(e.getMessage());
			return ResUtils.execRes("融云短信请求异常");
		}
		 
		HttpEntity<HashMap> request2 = new HttpEntity<>(reqMap,headers);
//		ResponseEntity<String> result = restTemplate.postForEntity(codeUrl, request2, String.class);
//		String resJson = result.getBody();
//		logger.info("发送短信返回结果---------:"+resJson);
//		String resCode = JSONObject.parseObject(resJson).getString("statusCode");
		SmsPo po = new SmsPo();
//		po.setSmsSendStatus(resCode);
		po.setPhone(phone);
		po.setCreateTime(new Date());
		po.setSmsCode(code);
		po.setSmsLong(smsMins);
		po.setUserId(userId);
		smsPoMapper.insert(po);
		return ResUtils.okRes();
	}

}
