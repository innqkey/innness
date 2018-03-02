package com.huisou.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.huisou.mapper.ChatHistoryPoMapper;
import com.huisou.mapper.ChatMessagePoMapper;
import com.huisou.po.ChatMessagePo;
import com.huisou.service.ChatMessageService;
import com.huisou.vo.AwardRecordVo;
import com.huisou.vo.PageTemp;

/**
* 类说明：
* @author 
* @version 创建时间：2018年2月27日 下午5:00:45
* 
*/
@Service
public class ChatMessageServiceImp implements ChatMessageService {
	
	@Autowired
	private ChatMessagePoMapper kefuMessagePoMapper ;
	@Override
	public void save(ChatMessagePo data) {
		kefuMessagePoMapper.insertSelective(data);
		
	}
	
	/**
	 * 获取对应的历史记录
	 */
	@Override
	public PageInfo<ChatMessagePo> listGetMessage(Integer fromUser, Integer toUser, Long messageid, PageTemp pageTemp) {
		PageHelper.startPage(pageTemp.getPageNum(), pageTemp.getPageSize());
		List<ChatMessagePo> list = kefuMessagePoMapper.listGetMessage(fromUser,toUser,messageid);
		return  new PageInfo<ChatMessagePo>(list);
	}

}
