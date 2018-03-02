package com.huisou.mapper;

import java.util.List;
import java.util.Map;

import com.huisou.po.SayPublisherPo;
import com.huisou.vo.SayPublisherVo;

public interface SayPublisherPoMapper {
    int deleteByPrimaryKey(Integer publisherId);

    int insert(SayPublisherPo record);

    int insertSelective(SayPublisherPo record);

    SayPublisherPo selectByPrimaryKey(Integer publisherId);

    int updateByPrimaryKeySelective(SayPublisherPo record);

    int updateByPrimaryKey(SayPublisherPo record);

	void delete(List<String> publisherId);

	List<SayPublisherVo> findAll(Map<String, String> maps);
}