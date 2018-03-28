package com.huisou.service;

import com.huisou.po.OrderPo;

public interface RebateRecordService {

	//支付成功后调用，所有返现逻辑
	public void rebate(OrderPo orderPo);
}
