package com.huisou.po;

import java.util.Date;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
@Table(name = "busi_chat_history")
public class ChatHistoryPo {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer chatHistoryId;

    private Integer fromUser;

    private Integer toUser;

    private String status;

    private Date createTime;

    private Date updateTime;

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

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status == null ? null : status.trim();
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }
}