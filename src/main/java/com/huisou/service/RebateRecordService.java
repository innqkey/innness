package com.huisou.service;

import com.github.pagehelper.PageInfo;
import com.huisou.po.OrderPo;
import com.huisou.vo.PageTemp;
import com.huisou.vo.RebateRecordVo;

public interface RebateRecordService {

	//支付成功后调用，所有返现逻辑
	public void rebate(OrderPo orderPo);
	
	//查找返现记录
	public PageInfo<RebateRecordVo> findRebateRecordByuserId(Integer userId, PageTemp pageTemp);
}
