package com.huisou.mapper;

import java.util.List;

import com.huisou.po.TemplateSendHistoryPo;
import com.huisou.vo.TemplateHistoryVo;

public interface TemplateSendHistoryPoMapper {
    int deleteByPrimaryKey(Integer templateHistoryId);

    int insert(TemplateSendHistoryPo record);

    int insertSelective(TemplateSendHistoryPo record);

    TemplateSendHistoryPo selectByPrimaryKey(Integer templateHistoryId);

    int updateByPrimaryKeySelective(TemplateSendHistoryPo record);

    int updateByPrimaryKey(TemplateSendHistoryPo record);

	void changeExistStatus(Integer historyId);

	List<TemplateHistoryVo> findAll();
}