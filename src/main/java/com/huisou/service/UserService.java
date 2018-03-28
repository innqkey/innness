package com.huisou.service;

import java.util.List;
import java.util.Map;

import com.github.pagehelper.PageInfo;
import com.huisou.po.UserPo;
import com.huisou.vo.CustomerVo;
import com.huisou.vo.PageTemp;
import com.huisou.vo.UserVo;

/** 
* @author qinkai 
* @date 2018年1月31日
*/

public interface UserService {

	/**
	 * 用户管理列表
	 * @param para
	 * @param pageTemp
	 * @return
	 */
	PageInfo<UserPo> findAll(Map para, PageTemp pageTemp);

	/**
	 * 删除一个用户
	 * @param userId
	 */
	void deleteOne(List<String> userId);

//	/**
//	 * 查看用户详情
//	 * @param userId
//	 * @return
//	 */
//	UserVo findOne(Integer userId);

	/***
	 * 关联一个用户id
	 * @param userPo
	 */
	Integer addOne(UserPo userPo);

	/**
	 * 查找微信用户
	 * @param userId
	 * @return
	 */
	UserPo find(Integer userId);
	
	/**
	 * 查找微信用户
	 * @param userId
	 * @return
	 */
	UserPo find(Map paraMap);

	/**
	 * 更新微信用户
	 * @param userPo
	 */
	void updateOne(UserPo userPo);

	/**
	 * 资料下载时判断用户是否有足够积分
	 * @param userId
	 * @param valueOf
	 * @return 积分充足时返回true,反之false
	 */
	boolean getUserIntegral(Integer userId, Integer valueOf);

	UserPo getUserByOpenId(String openId);

	PageInfo<UserVo> findAllClassmate(String openId,PageTemp pageTemp);

	/**
	 * 根据userId 更新用户积分
	 * @param userId
	 * @param integral
	 */
	void updateUserIntegral(Integer userId, Long integral);
	
	List<String> findAllOpenid();

	PageInfo<CustomerVo> findCustomer(Integer userId, PageTemp pageTemp,String userName);

	PageInfo<UserVo> finCutomerByPara(PageTemp pageTemp, Map<String, String> para);

	List<UserPo> findAllUsers();

}
