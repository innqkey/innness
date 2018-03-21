package com.huisou.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.huisou.mapper.AgentPoMapper;
import com.huisou.po.AgentPo;
import com.huisou.service.AgentService;

/** 
* @author 作者 :yuhao 
* @version 创建时间：2018年3月15日 下午1:57:50 
* 类说明 
*/
@Service
public class AgentServiceImpl implements AgentService {
	
	@Autowired
	private AgentPoMapper agentPoMapper;

	@Override
	public void updateAgentUserId(List<Integer> list,Integer userId) {
		AgentPo agentPo = new AgentPo();
		agentPo.setUserId(userId);
		agentPo.setAgentUserId(0);
		agentPoMapper.updateByPrimaryKeySelective(agentPo);
		int length = list.size();
		int a = length/30;
		for(int i = 0; i<a-1; i++){
			List<Integer> sublist = list.subList(i*30, (i+1)*30);
			agentPoMapper.updateAgentUserId(sublist,userId);
		}
		agentPoMapper.updateAgentUserId(list.subList(a*30, length),userId);
	}
	
}
