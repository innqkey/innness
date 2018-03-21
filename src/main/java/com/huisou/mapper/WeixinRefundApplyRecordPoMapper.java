package com.huisou.mapper;

import com.huisou.po.WeixinRefundApplyRecordPo;

public interface WeixinRefundApplyRecordPoMapper {
    int deleteByPrimaryKey(Integer applyId);

    int insert(WeixinRefundApplyRecordPo record);

    int insertSelective(WeixinRefundApplyRecordPo record);

    WeixinRefundApplyRecordPo selectByPrimaryKey(Integer applyId);

    int updateByPrimaryKeySelective(WeixinRefundApplyRecordPo record);

    int updateByPrimaryKey(WeixinRefundApplyRecordPo record);
}