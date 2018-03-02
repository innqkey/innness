package com.huisou.po;
/**
* 类说明： 用户的聊天记录，包括存在的历史
* @author 
* @version 创建时间：2018年2月27日 下午4:55:45
* 
*/
public class ChatRecordVo {
	
	private Integer chatHistoryId;
	private Integer fromUser;
    private Integer toUser;

    private String fromUsername;

    private String toUsername;
	
	private String message;
	
	private String msgtype;
	
	public Integer getChatHistoryId() {
		return chatHistoryId;
	}
	public void setChatHistoryId(Integer chatHistoryId) {
		this.chatHistoryId = chatHistoryId;
	}
	public Integer getFromUser() {
		return fromUser;
	}
	public void setFromUser(Integer fromUser) {
		this.fromUser = fromUser;
	}
	
	public Integer getToUser() {
		return toUser;
	}
	public void setToUser(Integer toUser) {
		this.toUser = toUser;
	}
	
	public String getFromUsername() {
		return fromUsername;
	}
	public void setFromUsername(String fromUsername) {
		this.fromUsername = fromUsername;
	}
	public String getToUsername() {
		return toUsername;
	}
	public void setToUsername(String toUsername) {
		this.toUsername = toUsername;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	public String getMsgtype() {
		return msgtype;
	}
	public void setMsgtype(String msgtype) {
		this.msgtype = msgtype;
	}
	
	
	
	
	
}
