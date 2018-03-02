package com.huisou.service;


public interface SmsService {

	public String sendSms(String token, String phone, Integer userId);
}
