package com.huisou.mapper;

import java.util.List;

import com.huisou.po.TemplateContentPo;
import com.huisou.vo.TemplateContentVo;

public interface TemplateContentPoMapper {
    int deleteByPrimaryKey(Integer msgTemplateId);

    int insert(TemplateContentPo record);

    int insertSelective(TemplateContentPo record);

    TemplateContentPo selectByPrimaryKey(Integer msgTemplateId);

    int updateByPrimaryKeySelective(TemplateContentPo record);

    int updateByPrimaryKey(TemplateContentPo record);

	List<TemplateContentVo> findAll();

	void changeStatus(Integer id);

	TemplateContentPo getById(Integer id);
}