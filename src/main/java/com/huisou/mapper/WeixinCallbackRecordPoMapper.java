package com.huisou.mapper;

import com.huisou.po.WeixinCallbackRecordPo;

public interface WeixinCallbackRecordPoMapper {
    int deleteByPrimaryKey(Long id);

    int insert(WeixinCallbackRecordPo record);

    int insertSelective(WeixinCallbackRecordPo record);

    WeixinCallbackRecordPo selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(WeixinCallbackRecordPo record);

    int updateByPrimaryKey(WeixinCallbackRecordPo record);
}