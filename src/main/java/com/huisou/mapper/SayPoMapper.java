package com.huisou.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.huisou.po.SayPo;

public interface SayPoMapper {
    int deleteByPrimaryKey(Integer sayId);

    int insert(SayPo record);

    int insertSelective(SayPo record);

    SayPo selectByPrimaryKey(Integer sayId);

    int updateByPrimaryKeySelective(SayPo record);

    int updateByPrimaryKey(SayPo record);

	List<SayPo> findAll(Map<String, String> maps);

	void increaseNum(@Param("sayId")Integer sayId, @Param("type")Integer type, @Param("num")Integer num);

	void delete(List<String> sayId);

	SayPo selectOne();

}