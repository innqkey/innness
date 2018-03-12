package com.huisou.vo;

import java.util.Date;

/**
* 类说明：
* @author 
* @version 创建时间：2018年3月8日 下午4:40:45
* 
*/
public class TemplateHistoryVo {
	 private Integer templateContentId;
	 private Integer templateHistoryId;
	 private Integer sendAmount;
	 private String keyword1;
	 private Date createTime;
	 private String templateType;
	 private String noticeType;
	
	public String getKeyword1() {
		return keyword1;
	}
	public void setKeyword1(String keyword1) {
		this.keyword1 = keyword1;
	}
	public Integer getTemplateHistoryId() {
		return templateHistoryId;
	}
	public void setTemplateHistoryId(Integer templateHistoryId) {
		this.templateHistoryId = templateHistoryId;
	}
	
	public Integer getTemplateContentId() {
		return templateContentId;
	}
	public void setTemplateContentId(Integer templateContentId) {
		this.templateContentId = templateContentId;
	}
	public Integer getSendAmount() {
		return sendAmount;
	}
	public void setSendAmount(Integer sendAmount) {
		this.sendAmount = sendAmount;
	}
	public Date getCreateTime() {
		return createTime;
	}
	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}
	public String getTemplateType() {
		return templateType;
	}
	public void setTemplateType(String templateType) {
		this.templateType = templateType;
	}
	public String getNoticeType() {
		return noticeType;
	}
	public void setNoticeType(String noticeType) {
		this.noticeType = noticeType;
	}
	 
}
