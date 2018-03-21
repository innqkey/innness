package com.huisou.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.huisou.mapper.RefundPoMapper;
import com.huisou.po.RefundPo;
import com.huisou.service.RefundService;

/**
* 类说明：
* @author 
* @version 创建时间：2018年3月15日 下午3:20:36
* 
*/
@Service
public class RefundServiceImpl implements RefundService{

	@Autowired
	private RefundPoMapper refundPomapper;
	@Override
	public void saveRefund(RefundPo refundPo) {
		refundPomapper.insertSelective(refundPo);
	}
	@Override
	public RefundPo findById(Integer refundId) {
		return refundPomapper.selectByPrimaryKey(refundId);
	}
	@Override
	public void updateStatus(RefundPo refundPo) {
		refundPomapper.updateByPrimaryKeySelective(refundPo);
	}
	
	/**
	 * 通过refundNo发现对应的
	 */
	@Override
	public RefundPo findByRefundNo(String outRefundNo) {
		return refundPomapper.findByRefundNo(outRefundNo);
	}
	
}