package com.huisou.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.huisou.mapper.ChatHistoryPoMapper;
import com.huisou.po.ChatHistoryPo;
import com.huisou.po.ChatRecordVo;
import com.huisou.service.ChatHistoryService;

/**
* 类说明：kefu历史消息的实现类，用于各种消息的实现
* @author 
* @version 创建时间：2018年2月27日 下午4:07:52
* 
*/
@Service
public class ChatHistoryServiceImpl implements ChatHistoryService{
	
	@Autowired
	private ChatHistoryPoMapper mapper;
	/**
	 *  插入历史消息
	 */
	@Override
	public void insertHistory(ChatHistoryPo chatHistoryPo) {
		mapper.insertSelective(chatHistoryPo);
	}
	/**
	 * 获取聊天记录
	 */
	@Override
	public List<ChatRecordVo> getChatRecord(String userId) {
		List<ChatRecordVo> list = mapper.getChatRecord(userId);;
		return list;
	}
	@Override
	public void deleteRecord(Integer historyId) {
		mapper.updateStatus(historyId);
		
	}

}
