package com.huisou.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.huisou.constant.ContextConstant;
import com.huisou.mapper.AgentPoMapper;
import com.huisou.mapper.UserPoMapper;
import com.huisou.po.AgentPo;
import com.huisou.po.UserPo;
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
	
	
	@Autowired
	private UserPoMapper userPoMapper;
	
//	@Autowired
//	private AgentCopyPoMapper agentCopyPoMapper;

	private  static  final Logger logger = LoggerFactory.getLogger(AgentServiceImpl.class);
	@Override
	public String queryAgentIdsByAgentId(Integer agentId) {
		// TODO Auto-generated method stub
		String userIds = agentPoMapper.queryAgentIdsByAgentId(agentId);
		//System.out.println("userIds========"+userIds);
		return userIds;
	}

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

	@Override
	public AgentPo queryByUserId(Integer userId) {
		// TODO Auto-generated method stub
		List<AgentPo> agents = agentPoMapper.selectPoByUserId(userId);
		if(null!=agents&&agents.size()>0){
			return agents.get(0);
		}else{
			return null;
		}
	}

	@Override
	public void insertAgent(AgentPo po) {
		// TODO Auto-generated method stub
		synchronized(String.valueOf(po.getUserId()).intern()){
			List<AgentPo> agents = agentPoMapper.selectPoByUserId(po.getUserId());
			if(null==agents||agents.size()==0){
				agentPoMapper.insertSelective(po);
			}
    	}
	}

	@Override
	public void queryByAgentEmp(Integer userId) {
		// TODO Auto-generated method stub
		Map map = new HashMap();
		if(null!=userId){
			map.put("userId", userId);
		}else{
			map.put("classIdNull", "N");
//			map.put("isAgency", "3");
//			map.put("userId", userId);
		}
		int i=1;
		List<UserPo> userList = userPoMapper.findAllUserByMap(map);
		
		for(UserPo po : userList){
			logger.info(userId+"==userId下属第一级--"+po.getNickname());
			Integer newUserId = po.getUserId();
			Map classMap = new HashMap();
			classMap.put("classmateUserId", newUserId);
			List<UserPo> childPo = userPoMapper.findAllUserByMap(classMap);
			ite(childPo,i,po,newUserId,newUserId);
		}
		
	}

	
	private void ite(List<UserPo> userList,int i,UserPo classPo,Integer agentUserId,Integer classEmpId) {
		if(null!=userList&&userList.size()>0){
			i++;
			for(UserPo po : userList){
				//如果会员等级不为null，则不迭代
				if(null!=po.getMemberSetId()){
					continue;
				}
				Integer userId = po.getUserId();
				Map classMap = new HashMap();
				logger.info("用户名--"+po.getNickname()+";层级=="+i);
				
				List<AgentPo> agentLists = agentPoMapper.selectPoByUserId(po.getUserId());
				//如果是用户，则插入分销人
				if(null!=agentLists&&agentLists.size()>0){
					AgentPo copyPo = agentLists.get(0);
//					copyPo.setUserId(po.getUserId());
					copyPo.setAgentUserId(agentUserId);
					agentPoMapper.updateByPrimaryKeySelective(copyPo);
				}else{
					AgentPo copyPo = new AgentPo();
					copyPo.setUserId(po.getUserId());
					copyPo.setClassmateUserId(classPo.getUserId());
					copyPo.setAgentUserId(agentUserId);
					copyPo.setInitialAgentUserId(agentUserId);
					copyPo.setClassEmpId(classEmpId);
					agentPoMapper.insert(copyPo);
				}
				classMap.put("classmateUserId", userId);
				
				List<UserPo> childPo = userPoMapper.findAllUserByMap(classMap);
				if(!ContextConstant.AGENT_USER.equals(po.getIsAgency())){
					ite(childPo,i,po,po.getUserId(),classEmpId);
				}else{
					ite(childPo,i,po,agentUserId,classEmpId);
				}
				
			}
		}else{
			logger.info("-----------------------------");
		}
	}
	
}
