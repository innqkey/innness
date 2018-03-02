package com.huisou.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.huisou.po.ResourcesThumbsPo;
import com.huisou.vo.ResourcesThumbsVo;

public interface ResourcesThumbsPoMapper {
    int deleteByPrimaryKey(Integer thumbsId);

    int insert(ResourcesThumbsPo record);

    int insertSelective(ResourcesThumbsPo record);

    ResourcesThumbsPo selectByPrimaryKey(Integer thumbsId);

    int updateByPrimaryKeySelective(ResourcesThumbsPo record);

    int updateByPrimaryKey(ResourcesThumbsPo record);

	void deleteOne(@Param("resId")Integer resId, @Param("userId")Integer userId);

	ResourcesThumbsPo findOne(@Param("resId")Integer resId, @Param("userId")Integer userId);

	List<ResourcesThumbsVo> findAll(Map<String, String> maps);

	List<ResourcesThumbsVo> selectAll(Integer sayId);

	void updateThumbs(@Param("userId")Integer userId, @Param("resId")Integer resId);
}