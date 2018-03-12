package com.huisou.cache;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSON;
import com.huisou.config.redis.IRedisBaseDao;
import com.huisou.constant.ContextConstant;

/**
 * 用于保存每一次用户的信息的
 * @author Administrator
 *
 */
@Service
public class RedisSmsCodeCache {
	
	private  static  final Logger logger = LoggerFactory.getLogger(RedisSmsCodeCache.class);
	@Autowired
	@Qualifier("redis")
	private IRedisBaseDao<String> redis;
	
	/**
	 * 添加用户信息到redis
	 * @param userid
	 * @param userInfo
	 */
	public void addSmsCode(String token,String code){
		logger.info("token;验证码===:"+token+";"+code);
		redis.put("sms",token, code, ContextConstant.REDES_DATABASE1);
	}
	
	/**
	 * 从redis获取用户信息
	 * @param userid
	 * @return
	 */
	public String getSmsCode(String token){
		String code = String.valueOf(redis.getMap("sms",token, ContextConstant.REDES_DATABASE1));
		return code;
	}
	
}
