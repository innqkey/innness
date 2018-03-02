package com.huisou.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.huisou.po.MyStudyPo;
import com.huisou.vo.MyStudyVo;

public interface MyStudyPoMapper {
    int deleteByPrimaryKey(Integer myStudyId);

    int insert(MyStudyPo record);

    int insertSelective(MyStudyPo record);

    MyStudyPo selectByPrimaryKey(Integer myStudyId);

    int updateByPrimaryKeySelective(MyStudyPo record);

    int updateByPrimaryKey(MyStudyPo record);

	List<MyStudyVo> findAll(Map<String, String> maps);

	List<MyStudyPo> findOne(@Param("userId")Integer userId, @Param("materialId")Integer materialId, @Param("resType")String resType);
}