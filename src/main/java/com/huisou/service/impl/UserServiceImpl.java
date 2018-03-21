package com.huisou.service.impl;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.commons.beanutils.BeanUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSON;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.huisou.constant.ContextConstant;
import com.huisou.mapper.CoursePoMapper;
import com.huisou.mapper.UserPoMapper;
import com.huisou.po.CoursePo;
import com.huisou.po.UserPo;
import com.huisou.service.UserService;
import com.huisou.vo.CustomerVo;
import com.huisou.vo.PageTemp;
import com.huisou.vo.UserVo;

/** 
* @author qinkai 
* @date 2018年1月31日
*/

@Service
public class UserServiceImpl implements UserService{

	@Autowired
	private UserPoMapper userPoMapper;
	
	@Autowired
	private CoursePoMapper coursePoMapper;
	
	private  static  final Logger logger = LoggerFactory.getLogger(UserServiceImpl.class);
	
	@Override
	public PageInfo<UserPo> findAll(Map para, PageTemp pageTemp) {
		PageHelper.startPage(pageTemp.getPageNum(), pageTemp.getPageSize());
		List<UserPo> voList = userPoMapper.findAllUser(para);
		return new PageInfo<UserPo>(voList);
	}

	@Override
	public void deleteOne(List<String> userId) {
		userPoMapper.deleteOne(userId);
	}

//	@Override
//	public UserVo findOne(Integer userId) {
//		return userPoMapper.findOne(userId);
//	}
	/***
	 * 重新修改添加操作，如果有的话就修改，
	 * 如果没有的话，听啊家
	 */
	@Override
	public Integer addOne(UserPo userPo) {
//		UserPo user = userPoMapper.getUserByOpenId(userPo.getOpenid());
//		if (user != null && user.getUserId() != null) {
//			userPo.setUserId(user.getUserId());
//			//当user原本的classUserId不为空的时候，那么就替换原本的userid
//			if (user.getClassmateUserId() != null) {
//				userPo.setClassmateUserId(user.getClassmateUserId());
//			}
//			return userPoMapper.updateByPrimaryKey(userPo);
//		}
		
		int i = 0;
		synchronized(userPo.getOpenid().intern()){
			logger.info("-----------------------"+userPo.getOpenid());
			try{
				UserPo user = userPoMapper.getUserByOpenId(userPo.getOpenid());
				if(null==user){
					i = userPoMapper.insertSelective(userPo);
				}
			}catch(Exception e){
				logger.info("授权插入数据重复======"+JSON.toJSONString(userPo));
			}
		}
		logger.info("执行完成======");
		return i;
	}

	@Override
	public UserPo find(Integer userId) {
		return userPoMapper.selectByPrimaryKey(userId);
	}

	@Override
	public void updateOne(UserPo userPo) {
		userPoMapper.updateByPrimaryKeySelective(userPo);
	}
	@Override
	public UserPo getUserByOpenId(String openId) {
		return userPoMapper.getUserByOpenId(openId);
	}


	public boolean getUserIntegral(Integer userId, Integer integral) {
		UserPo userPo = userPoMapper.selectByPrimaryKey(userId);
		if (null != userPo && null != userPo.getIntegralNum() && userPo.getIntegralNum().intValue() >= integral){
			return true;
		} else {
			return false;
		}
	}


	@Override
	public UserPo find(Map paraMap) {
		// TODO Auto-generated method stub
		List<UserPo> list = userPoMapper.findAllUser(paraMap);
		if(null!=list&&list.size()>0){
			return list.get(0);
		}
		return null;
	}
	/***
	 * 发现所有的同学
	 */
	@Override
	public PageInfo<UserVo> findAllClassmate(String openId,PageTemp pageTemp) {
		List<UserVo> volist = new ArrayList<>();
		PageHelper.startPage(pageTemp.getPageNum(), pageTemp.getPageSize());
		List<UserPo> list = userPoMapper.listByOpenIdFindClassUserId(openId);
		for (UserPo userPo : list) {
			UserVo userVo = new UserVo();
			try {
				BeanUtils.copyProperties(userVo, userPo);
			} catch (IllegalAccessException | InvocationTargetException e) {
				e.printStackTrace();
			}
			List<CoursePo> coursePoList = coursePoMapper.findAllPayCourseByUserid(userVo.getUserId());
			if(null==coursePoList || coursePoList.size()==0){
				userVo.setIsApply(ContextConstant.NO);
			}else{
				userVo.setIsApply(ContextConstant.YES);
			}
			userVo.setList(coursePoList);
			volist.add(userVo);
		}
		return new PageInfo<>(volist);
	}

	@Override
	public void updateUserIntegral(Integer userId, Long integral) {
		userPoMapper.updateUserIntegral(userId,integral);
	}

	@Override
	public List<String> findAllOpenid() {
		List<String> list = userPoMapper.findAllOpenid();
		return list;
	}

	@Override
	public PageInfo<CustomerVo> findCustomer(Integer userId, PageTemp pageTemp) {
		PageHelper.startPage(pageTemp.getPageNum(), pageTemp.getPageSize());
		List<CustomerVo> list = userPoMapper.findCustomer(userId);
		return new PageInfo<>(list);
	}

}
