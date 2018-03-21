package com.huisou.mapper;

import com.huisou.po.RefundPo;

public interface RefundPoMapper {
    int deleteByPrimaryKey(Integer refundId);

    int insert(RefundPo record);

    int insertSelective(RefundPo record);

    RefundPo selectByPrimaryKey(Integer refundId);

    int updateByPrimaryKeySelective(RefundPo record);

    int updateByPrimaryKey(RefundPo record);

	RefundPo findByRefundNo(String outRefundNo);
}