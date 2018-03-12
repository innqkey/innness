package com.huisou.po;

import java.util.Date;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
@Table(name = "busi_template_send_history")
public class TemplateSendHistoryPo {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer templateHistoryId;

    private Integer templateContentId;

    private String noticeType;

    private String templateId;

    private String templateType;

    private Integer sendAmount;

    private Date createTime;

    private Integer existStatus;

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

    public String getNoticeType() {
        return noticeType;
    }

    public void setNoticeType(String noticeType) {
        this.noticeType = noticeType == null ? null : noticeType.trim();
    }

    public String getTemplateId() {
        return templateId;
    }

    public void setTemplateId(String templateId) {
        this.templateId = templateId == null ? null : templateId.trim();
    }

    public String getTemplateType() {
        return templateType;
    }

    public void setTemplateType(String templateType) {
        this.templateType = templateType == null ? null : templateType.trim();
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

    public Integer getExistStatus() {
        return existStatus;
    }

    public void setExistStatus(Integer existStatus) {
        this.existStatus = existStatus;
    }
}