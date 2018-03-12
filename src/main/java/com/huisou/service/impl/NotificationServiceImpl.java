package com.huisou.service.impl;

import java.util.Collections;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.huisou.mapper.NotificationPoMapper;
import com.huisou.po.NotificationPo;
import com.huisou.service.NotificationService;
import com.huisou.vo.PageTemp;

/** 
* @author 作者 :yuhao 
* @version 创建时间：2018年3月8日 下午2:32:02 
* 类说明 
*/
@Service
public class NotificationServiceImpl implements NotificationService{
	@Autowired
	private NotificationPoMapper notificationPoMapper;

	@Override
	public PageInfo<NotificationPo> findAll(Integer userId,PageTemp pageTemp) {
		PageHelper.startPage(pageTemp.getPageNum(), pageTemp.getPageSize());
		List<NotificationPo> list = notificationPoMapper.findAll(userId);
		Collections.reverse(list);
		return new PageInfo<>(list);
	}

	@Override
	public void addOne(NotificationPo notificationPo) {
		notificationPoMapper.insertSelective(notificationPo);
	}
	
	
}
