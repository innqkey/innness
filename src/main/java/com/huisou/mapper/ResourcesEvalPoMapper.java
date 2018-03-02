package com.huisou.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.huisou.po.ResourcesEvalPo;
import com.huisou.vo.ResourcesEvalVo;

public interface ResourcesEvalPoMapper {
    int deleteByPrimaryKey(Integer evalId);

    int insert(ResourcesEvalPo record);

    int insertSelective(ResourcesEvalPo record);

    ResourcesEvalPo selectByPrimaryKey(Integer evalId);

    int updateByPrimaryKeySelective(ResourcesEvalPo record);

    int updateByPrimaryKey(ResourcesEvalPo record);

	List<ResourcesEvalVo> findAll(Map<String, String> maps);

	List<ResourcesEvalVo> selectAll(Integer sayId);

	void updateStatus(Map params);
	
	Integer selectavgEvalLevel(@Param(value ="videoAudioId")Integer videoAudioId, @Param(value ="videoAudioType")String videoAudioType);

	List<ResourcesEvalVo> findAllByMap(Map<String, String> map);
	
	List<ResourcesEvalVo> findAllByMapAndStatus(Map<String, String> map);
}