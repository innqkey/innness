package com.huisou.service;

import java.util.List;

import com.huisou.po.ChatHistoryPo;
import com.huisou.po.ChatRecordVo;


/**
* 类说明：
* @author 
* @version 创建时间：2018年2月27日 下午4:07:26
* 
*/
public interface ChatHistoryService {

	void insertHistory(ChatHistoryPo kefuHistoryPo);

	List<ChatRecordVo> getChatRecord(String userId);

	void deleteRecord(Integer historyId);

}
