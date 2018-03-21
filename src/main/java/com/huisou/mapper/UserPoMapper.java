package com.huisou.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.huisou.po.UserPo;
import com.huisou.vo.CustomerVo;
import com.huisou.vo.UserVo;

public interface UserPoMapper {
	 int deleteByPrimaryKey(Integer userId);

	    int insert(UserPo record);

	    int insertSelective(UserPo record);

	    UserPo selectByPrimaryKey(Integer userId);

	    int updateByPrimaryKeySelective(UserPo record);

	    int updateByPrimaryKey(UserPo record);

		List<UserPo> findAllUser(Map para);

		void deleteOne(List<String> userId);

		UserVo findOne(Integer userId);

		UserPo getUserByOpenId(String openid);

		/**
		 * 通过openid发现userid
		 * 然后查询出对应的用户的userId
		 * @param openId
		 * @return
		 */
		List<UserPo> listByOpenIdFindClassUserId(String openid);

		void updateUserIntegral(@Param("userId")Integer userId, @Param("integral")Long integral);

		List<String> findAllOpenid();

		List<CustomerVo> findCustomer(@Param("userId")Integer userId);

}