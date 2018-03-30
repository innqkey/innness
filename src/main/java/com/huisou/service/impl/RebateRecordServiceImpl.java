package com.huisou.service.impl;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.huisou.constant.ContextConstant;
import com.huisou.mapper.AgentPoMapper;
import com.huisou.mapper.MemberSetPoMapper;
import com.huisou.mapper.OrderPoMapper;
import com.huisou.mapper.RebateRecordPoMapper;
import com.huisou.mapper.ResRebateSetPoMapper;
import com.huisou.mapper.UserAccountPoMapper;
import com.huisou.po.AgentPo;
import com.huisou.po.MemberSetPo;
import com.huisou.po.OrderPo;
import com.huisou.po.RebateRecordPo;
import com.huisou.po.ResRebateSetPo;
import com.huisou.po.UserPo;
import com.huisou.service.AgentService;
import com.huisou.service.RebateRecordService;
import com.huisou.service.UserService;
import com.huisou.vo.PageTemp;
import com.huisou.vo.RebateRecordVo;

@Service
public class RebateRecordServiceImpl implements RebateRecordService {

	@Autowired
	private AgentPoMapper agentMapper;
	
	@Autowired
	private RebateRecordPoMapper rebRecordPoMapper;
	
	@Autowired
	private UserService userSer;
	
	@Autowired
	private OrderPoMapper orderPoMapper;
	
	@Autowired
	private MemberSetPoMapper memSetMapper;
	
	@Autowired
	private UserAccountPoMapper accountMapper;
	
	@Autowired
	private ResRebateSetPoMapper resSetPoMapper;
	
	@Autowired
	private AgentService agentSer;
	
	@Value(value="${employee.rebate.sign}")
	private String rebateSign;
	
	private  static  final Logger logger = LoggerFactory.getLogger(RebateRecordServiceImpl.class);
	@Override
	public void rebate(OrderPo orderPo) {
		if(null==orderPo||!ContextConstant.PAY_STATUS_SUCCESS.equals(orderPo.getPayStatus())){
			return ;
		}
		Map paras = new HashMap();
		paras.put("resOrderId", orderPo.getOrderId());
		paras.put("resOrderNo", orderPo.getOrderNo());
		List<RebateRecordPo> recordList = rebRecordPoMapper.findRebateRecordByMap(paras);
		if(null!=recordList&&recordList.size()>0){
			return ;
		}
		logger.info("第一步,支付成功后调用,先查询他的代理人,判断代理人是否存在,如果存在则继续给代理人返现");
		// TODO Auto-generated method stub
		//第一步,支付成功后调用,先查询他的代理人,判断代理人是否存在,如果存在则继续给代理人返现
		Integer userId = orderPo.getUserId();
		List<AgentPo> agentList = agentMapper.selectPoByUserId(userId);
		if(null!=agentList&&agentList.size()>0){
			Integer agentUserId = agentList.get(0).getAgentUserId();
			logger.info("代理人id-----------"+agentUserId);
			//第二步，存在代理人,满足返现条件，插入返现记录表,更新代理人（会员）账户余额
			if(null!=agentUserId&&agentUserId>0){
				//查询代理人，判断代理人是否会员，会员则返利，更新会员账户余额
				logger.info("第二步，存在代理人,满足返现条件，插入返现记录表,更新代理人（会员）账户余额");
				UserPo agentUserPo = userSer.find(agentUserId);
				//代理人是员工，查看是否配置返现开关
				boolean isEmp = ContextConstant.YES.equals(rebateSign)&&ContextConstant.AGENT_EMP.equals(agentUserPo.getIsAgency());
				boolean isRebate = null!=agentUserPo.getMemberSetId()&&agentUserPo.getMemberSetId()>0&&!ContextConstant.AGENT_EMP.equals(agentUserPo.getIsAgency());
				boolean isProxy = ContextConstant.AGENT_PROXY.equals(agentUserPo.getIsAgency());
				logger.info("返现人是否员工=="+isEmp+";返现人是会员并不是员工=="+isRebate+";返现人是否代理人=="+isProxy);
				//如果是员工（开启返现）或者会员则返现
				if(isRebate||isProxy){
					//第三步，更新代理（会员）账户余额
					logger.info("第三步，更新代理（会员）账户余额");
					
					Map req = new HashMap();
					req.put("resId", orderPo.getResId());
					req.put("resType", orderPo.getResType());
					req.put("memberSetId", null!=agentUserPo.getMemberSetId()?agentUserPo.getMemberSetId():ContextConstant.REBATE_LEVEL);
					List<ResRebateSetPo> rebSetList = resSetPoMapper.findByPara(req);
					if(null!=rebSetList&&rebSetList.size()>0){
						ResRebateSetPo rebSetPo = rebSetList.get(0);
						Long rebateMoney = rebSetPo.getResRebateMoney();
						accountMapper.updateAccountByRebate(agentUserId, rebateMoney);
						
						//插入返现记录
						RebateRecordPo rebPo = new RebateRecordPo();
						rebPo.setBuyUserId(userId);
						rebPo.setResOrderId(orderPo.getOrderId());
						rebPo.setResOrderNo(orderPo.getOrderNo());
						rebPo.setResOrderType(orderPo.getResType());
						rebPo.setOrderRebateMoney(rebateMoney);
						rebPo.setRebateUserId(agentUserId);
						rebPo.setCreateTime(new Date());
						rebRecordPoMapper.insertSelective(rebPo);
					}
				}
			}
		}
		//第四步,是会员,如果没有代理人,则判断是否可以升级会员等级
		//查询user,判断是否会员
		UserPo userPo = userSer.find(userId);
		//用户所有付过的总金额
		int payAllMoney = orderPoMapper.findPayAllMoney(userId);
		MemberSetPo memSerPo = memSetMapper.findMinLevelByMoney(payAllMoney);
		logger.info("消费总额是否满足升级条件---"+memSerPo.getMemberSetId());
		//如果消费总额>最小会员等级，则生成会员或者升级会员等级
		if(null!=memSerPo.getMemberSetId()&&memSerPo.getMemberSetId()>0){
			
			//如果是第一次升级为会员，则下面所有分享客户改为子集
			if(null==userPo.getMemberSetId()&&ContextConstant.AGENT_USER.equals(userPo.getIsAgency())){
				logger.info("普通用户第一次升级为会员，则下面所有分享客户改为子集------");
				agentSer.queryByAgentEmp(userId);
			}
			
			if(null==userPo.getMemberSetId()||userPo.getMemberSetId()<memSerPo.getMemberSetId()){
				logger.info("第四步,升级会员等级");
				userPo.setMemberSetId(memSerPo.getMemberSetId());
				userSer.updateOne(userPo);
			}
		}
	}
	@Override
	public PageInfo<RebateRecordVo> findRebateRecordByuserId(Integer userId, PageTemp pageTemp) {
		PageHelper.startPage(pageTemp.getPageNum(), pageTemp.getPageSize());
		List<RebateRecordVo> list = rebRecordPoMapper.findRebateRecordByuserId(userId);
		return new PageInfo<>(list);
	}

}
