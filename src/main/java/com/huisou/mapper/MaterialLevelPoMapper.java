package com.huisou.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.huisou.po.MaterialLevelPo;

public interface MaterialLevelPoMapper {
    int deleteByPrimaryKey(Integer materialLevelId);

    int insert(MaterialLevelPo record);

    int insertSelective(MaterialLevelPo record);

    MaterialLevelPo selectByPrimaryKey(Integer materialLevelId);

    int updateByPrimaryKeySelective(MaterialLevelPo record);

    int updateByPrimaryKey(MaterialLevelPo record);

	void deleteOne(@Param("materialLevelId")List<String> materialLevelId);

	List<MaterialLevelPo> findAll(Map<String, String> maps);

	void updateLevelNum(@Param("materialLevelId")Integer materialLevelId, @Param("num")Integer num);
}