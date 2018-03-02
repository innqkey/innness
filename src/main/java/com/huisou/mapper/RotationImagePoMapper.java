package com.huisou.mapper;

import java.util.List;
import java.util.Map;

import com.huisou.po.RotationImagePo;

public interface RotationImagePoMapper {
    int deleteByPrimaryKey(Integer rotationImageId);

    int insert(RotationImagePo record);

    int insertSelective(RotationImagePo record);

    RotationImagePo selectByPrimaryKey(Integer rotationImageId);

    int updateByPrimaryKeySelective(RotationImagePo record);

    int updateByPrimaryKey(RotationImagePo record);

	List<RotationImagePo> search(Map map);
}