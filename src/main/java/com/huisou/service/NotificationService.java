package com.huisou.service;

import com.github.pagehelper.PageInfo;
import com.huisou.po.NotificationPo;
import com.huisou.vo.PageTemp;

/** 
* @author 作者 :yuhao 
* @version 创建时间：2018年3月8日 下午2:31:49 
* 类说明 
*/
public interface NotificationService {

	PageInfo<NotificationPo> findAll(Integer userId,PageTemp pageTemp);
	
	void addOne(NotificationPo notificationPo);
}
