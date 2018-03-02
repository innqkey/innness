package com.common;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Set;

import com.alibaba.fastjson.JSON;
import com.common.MD5Util;

public class SDKTestSendTemplateSMS {
	public static String baseUrl = "https://app.cloopen.com:8883";
	public static String appId = "8aaf07086150ec5001616a204b0c07ad";
	public static String appToken = "6669d107f47ac98e98ef43eaa2a3247f";
	public static SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmss");
	public static String interUrl = "/2013-12-26/Accounts/"+appId+"/SMS/TemplateSMS?sig="+
			MD5Util.md5Encode(appId+appToken+sdf.format(new Date()));
	public static void main(String[] args) {
		HashMap reqMap = new HashMap(); 
		reqMap.put("to", "15372059271");
		reqMap.put("appId", appId);
		reqMap.put("templateId", "1");
		String[] params = {"4512","180s"};
		reqMap.put("datas", params);
		String jsonMap = JSON.toJSONString(reqMap);
		System.out.println(jsonMap);
//		{"to":"13911281234,15010151234,13811431234","appId":
//			 "ff8080813fc70a7b013fc72312324213","templateId":"1","datas":["替换内容","替换内容"]}
		// 请使用管理控制台中已创建应用的APPID。
//		result = restAPI.sendTemplateSMS("号码1,号码2等","模板Id" ,new String[]{"模板内容1","模板内容2"});
//		System.out.println("SDKTestGetSubAccounts result=" + result); 
//		if("000000".equals(result.get("statusCode"))){
//			//正常返回输出data包体信息（map）
//			HashMap<String,Object> data = (HashMap<String, Object>) result.get("data");
//			Set<String> keySet = data.keySet();
//			for(String key:keySet){ 
//					Object object = data.get(key); 
//					System.out.println(key +" = "+object); 
//			}
//		}else{
//			//异常返回输出错误码和错误信息
//			System.out.println("错误码=" + result.get("statusCode") +" 错误信息= "+result.get("statusMsg"));
//		}
	}
}
