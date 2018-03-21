package com.huisou.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.huisou.mapper.CommonUserSetPoMapper;
import com.huisou.po.CommonUserSetPo;
import com.huisou.service.UserSetService;

/**
* 类说明：
* @author 
* @version 创建时间：2018年3月15日 下午3:36:46
* 
*/
@Service
public class UserSetServiceImpl implements UserSetService {

	@Autowired
	private CommonUserSetPoMapper commonUserSetPoMapper;
	
	
	@Override
	public Boolean findByPhone(String phone) {
		List<CommonUserSetPo> list = commonUserSetPoMapper.findByPhone(phone);
		boolean result = false;
		if (list != null && list.size() > 0) {
			result = true;
		}
		
		return result;
	}

}
