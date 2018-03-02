package com.huisou.service;

import java.util.List;

import com.github.pagehelper.PageInfo;
import com.huisou.po.ChatMessagePo;
import com.huisou.vo.PageTemp;

/**
* 类说明：
* @author 
* @version 创建时间：2018年2月27日 下午5:00:09
* 
*/
public interface ChatMessageService {

	void save(ChatMessagePo data);

	PageInfo<ChatMessagePo> listGetMessage(Integer fromUser, Integer toUser, Long messageid, PageTemp pageTemp);


}
