package com.huisou.cache;

import java.io.IOException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSON;
import com.common.JacksonUtil;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.huisou.config.redis.IRedisBaseDao;
import com.huisou.constant.ContextConstant;
import com.huisou.po.UserPo;

/**
 * 用于保存每一次用户的信息的
 * @author Administrator
 *
 */
@Service
public class RedisUserTokenCache {
	
	private  static  final Logger logger = LoggerFactory.getLogger(RedisUserTokenCache.class);
	@Autowired
	@Qualifier("redis")
	private IRedisBaseDao<String> redis;
	
	/**
	 * 添加用户信息到redis
	 * @param userid
	 * @param userInfo
	 */
	public void addUserToken(String userToken,UserPo userPo){
		synchronized(RedisUserTokenCache.class){
			logger.info("userToken===:"+userToken+";openId=="+userPo.getOpenid());
			redis.set(userToken, JSON.toJSONString(userPo), ContextConstant.REDES_DATABASE1);
//			redis.setTimeout(token, code, 60*2, ContextConstant.REDES_DATABASE1);
		}
	}
	
	/**
	 * 从redis获取用户信息
	 * @param userid
	 * @return
	 */
	public UserPo getUserPoByToken(String userToken){
		String userJson = redis.get(userToken, ContextConstant.REDES_DATABASE1);
		try {
			return JacksonUtil.toObject(userJson, UserPo.class);
		} catch (JsonParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (JsonMappingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
}
