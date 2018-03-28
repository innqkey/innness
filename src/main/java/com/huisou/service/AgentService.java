package com.huisou.service;

import java.util.List;

import com.huisou.po.AgentPo;

/** 
* @author 作者 :yuhao 
* @version 创建时间：2018年3月15日 下午1:56:22 
* 类说明 
*/
public interface AgentService {


	public String queryAgentIdsByAgentId(Integer agentId);

	void updateAgentUserId(List<Integer> list,Integer userId);
	
	public AgentPo queryByUserId(Integer userId);
	
	void insertAgent(AgentPo po);

	void queryByAgentEmp(Integer userId);
}
