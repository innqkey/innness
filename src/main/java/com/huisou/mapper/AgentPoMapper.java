package com.huisou.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.huisou.po.AgentPo;

public interface AgentPoMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(AgentPo record);

    int insertSelective(AgentPo record);

    AgentPo selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(AgentPo record);

    int updateByPrimaryKey(AgentPo record);

    
    public String queryAgentIdsByAgentId(Integer agentId);


	void updateAgentUserId(@Param(value="list")List<Integer> list,@Param(value="userId")Integer userId);

	List<AgentPo> selectPoByUserId(Integer userId);
}