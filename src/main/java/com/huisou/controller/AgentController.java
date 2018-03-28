package com.huisou.controller;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.common.ConvertUtil;
import com.common.JacksonUtil;
import com.common.ResUtils;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.huisou.po.UserAccountPo;
import com.huisou.po.UserPo;
import com.huisou.service.AgentService;
import com.huisou.service.UserAccountService;
import com.huisou.service.UserService;
import com.huisou.po.OrderPo;
import com.huisou.service.AgentService;
import com.huisou.service.OrderService;
import com.huisou.service.RebateRecordService;

@RestController
@RequestMapping(value = "/agent")
public class AgentController extends BaseController {

	@Autowired
	private AgentService agentSer;
	@Autowired
	private UserService userService;
	@Autowired
	private UserAccountService userAccountService;
	@Autowired
	private RebateRecordService rebSer;
	
	@Autowired
	private OrderService orderService;
	
	@RequestMapping(value = "/queryAgentIdsByAgentId")
	public String queryAgentIdsByAgentId(Integer agentId) {
		// TODO Auto-generated method stub
		String userIds = agentSer.queryAgentIdsByAgentId(agentId);
		System.out.println("userIds========"+userIds);
		return ResUtils.okRes(userIds);
	}
	
	@RequestMapping(value = "/queryByAgentEmp")
	public String queryByAgentEmp(Integer userId) {
		// TODO Auto-generated method stub
		agentSer.queryByAgentEmp(userId);
		return ResUtils.okRes();
	}
	
	@RequestMapping(value = "/queryAllUser")
	public String queryAllUser(){
		List<UserPo> userPos = userService.findAllUsers();
		if (null != userPos && userPos.size() > 0){
			List<UserAccountPo> userAccountPos;
			try {
				userAccountPos = ConvertUtil.convertDtoAndVo(userPos, UserAccountPo.class);
				if (null != userAccountPos && userAccountPos.size() > 0){
					userAccountService.insertUserAccount(userAccountPos);
				}
				
			} catch (InstantiationException | IllegalAccessException e) {
				e.printStackTrace();
				return ResUtils.execRes();
			}
			return ResUtils.okRes();
		}
		return ResUtils.okRes(null);
	}
		
	@RequestMapping(value = "/rebSer")
	public String rebSer(Integer orderId) {
		OrderPo orderPo = orderService.selectOne(orderId);
//		rebSer.rebate(orderPo);
		return ResUtils.okRes();
	}
	
}
