package com.huisou.mapper;

import com.huisou.po.WeixinRefundNotifyRecordPo;

public interface WeixinRefundNotifyRecordPoMapper {
    int deleteByPrimaryKey(Integer refundNotifyId);

    int insert(WeixinRefundNotifyRecordPo record);

    int insertSelective(WeixinRefundNotifyRecordPo record);

    WeixinRefundNotifyRecordPo selectByPrimaryKey(Integer refundNotifyId);

    int updateByPrimaryKeySelective(WeixinRefundNotifyRecordPo record);

    int updateByPrimaryKey(WeixinRefundNotifyRecordPo record);
}