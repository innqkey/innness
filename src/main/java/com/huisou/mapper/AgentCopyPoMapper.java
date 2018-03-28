package com.huisou.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.huisou.po.AgentCopyPo;

public interface AgentCopyPoMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(AgentCopyPo record);

    int insertSelective(AgentCopyPo record);

    AgentCopyPo selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(AgentCopyPo record);

    int updateByPrimaryKey(AgentCopyPo record);

    
    public String queryAgentIdsByAgentId(Integer agentId);


	void updateAgentUserId(@Param(value="list")List<Integer> list,@Param(value="userId")Integer userId);

	List<AgentCopyPo> selectPoByAgent(Integer userId);
}