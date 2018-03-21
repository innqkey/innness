package com.huisou.service;

import com.huisou.po.RefundPo;

/**
* 类说明：
* @author 
* @version 创建时间：2018年3月15日 下午3:19:48
* 
*/
public interface RefundService {

	void saveRefund(RefundPo refundPo);

	RefundPo findById(Integer refundId);

	void updateStatus(RefundPo refundPo);

	RefundPo findByRefundNo(String outRefundNo);

}
