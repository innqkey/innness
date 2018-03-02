package com.huisou.controller;

import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.common.ResUtils;
import com.github.pagehelper.PageInfo;
import com.huisou.po.ChatMessagePo;
import com.huisou.po.ChatRecordVo;
import com.huisou.service.ChatHistoryService;
import com.huisou.service.ChatMessageService;
import com.huisou.vo.PageTemp;


/**
 *  目前暂时不用
* 类说明： 用户获取聊天信息的历史记录，历史信息的删除动作等
* @author 
* @version 创建时间：2018年2月27日 下午3:40:43
* 
*/
//@RestController
//@RequestMapping("/chat")
public class ChatController {
	
	@Autowired
	private ChatHistoryService chatHistoryService;
	@Autowired
	private ChatMessageService chatMessageService;
	
	
	/**
	 * 获取消息记录
	 * @param userId
	 * @return
	 */
	@RequestMapping("/getRecord")
	public String getRecord(String userId) {
		List<ChatRecordVo> chatRecordList;
		try {
			if (StringUtils.isBlank(userId) || !StringUtils.isNumeric(userId)) {
				return ResUtils.execRes("参数错误");
			}
			chatRecordList = chatHistoryService.getChatRecord(userId);
		} catch (Exception e) {
			e.printStackTrace();
			return ResUtils.execRes();
		}
		return ResUtils.okRes(chatRecordList);
		
	}
	
	/**
	 * 发送者和接收者都是 都有可以能是，所以进行对换查找
	 * @param userId
	 * @param pageTemp
	 * @return
	 */
	@RequestMapping("/getMessage")
	public String getMessage(String fromUser,String toUser,Long messageid,PageTemp pageTemp) {
		if (fromUser == null || toUser == null || !StringUtils.isNumeric(fromUser) || 
				!StringUtils.isNumeric(toUser)) {
			return ResUtils.execRes("参数错误");
		}  
		
		PageInfo<ChatMessagePo> chatList;
		try {
			chatList = chatMessageService.listGetMessage(Integer.valueOf(fromUser),Integer.valueOf(toUser),messageid,pageTemp);
		} catch (Exception e) {
			e.printStackTrace();
			return ResUtils.execRes();
		}
		return ResUtils.okRes(chatList);
		
	}
	
	/**
	 * 用于聊天记录的删除
	 * @param historyId
	 * @return
	 */
	@RequestMapping("/deleteRecord")
	public String deleteRecord(Integer historyId) {
		chatHistoryService.deleteRecord(historyId);
		return ResUtils.okRes();
	}
	
}
