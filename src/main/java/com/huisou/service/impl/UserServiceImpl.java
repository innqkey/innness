package com.huisou.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.huisou.mapper.UserPoMapper;
import com.huisou.po.UserPo;
import com.huisou.service.UserService;
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
		UserPo user = userPoMapper.getUserByOpenId(userPo.getOpenid());
		if (user != null && user.getUserId() != null) {
			userPo.setUserId(user.getUserId());
			//当user原本的classUserId不为空的时候，那么就替换原本的userid
			if (user.getClassmateUserId() != null) {
				userPo.setClassmateUserId(user.getClassmateUserId());
			}
			return userPoMapper.updateByPrimaryKey(userPo);
		}
		
		return userPoMapper.insertSelective(userPo);
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
		if (null != userPo && userPo.getIntegralNum().intValue() >= integral){
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
	public List<UserPo> findAllClassmate(String openId) {
		List<UserPo> list = null;
		list= userPoMapper.listByOpenIdFindClassUserId(openId);
		UserPo userByOpenId = userPoMapper.getUserByOpenId(openId);
		if (userByOpenId.getClassmateUserId() != null) {
			UserPo userPo = userPoMapper.selectByPrimaryKey(userByOpenId.getUserId());
			if (userPo != null) {
				if (list != null && list.size() > 0) {
					list.add(userPo);
				}else {
					list = new ArrayList<UserPo>();
					list.add(userPo);
				}
			}
		}
		
		return list;
	}

	@Override
	public void updateUserIntegral(Integer userId, Long integral) {
		userPoMapper.updateUserIntegral(userId,integral);
	}

}
