package com.huisou.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.huisou.po.MaterialPo;
import com.huisou.vo.MaterialVo;

public interface MaterialPoMapper {
    int deleteByPrimaryKey(Integer materialId);

    int insert(MaterialPo record);

    int insertSelective(MaterialPo record);

    MaterialPo selectByPrimaryKey(Integer materialId);

    int updateByPrimaryKeySelective(MaterialPo record);

    int updateByPrimaryKey(MaterialPo record);

	void deleteOne(@Param("materialId")List<String> materialId);

	List<MaterialVo> findAll(Map<String, String> maps);

	void increaseNum(@Param("materialId")Integer materialId);
}