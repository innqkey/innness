package com.huisou.po;

import java.util.Date;
import java.util.regex.Matcher;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import com.huisou.constant.ContextConstant;
@Table(name = "busi_notification")
public class NotificationPo {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer notificationId;

    private Integer userId;

    private String openId;

    private Integer templateId;

    private String notificationType;

    private String notificationContext;

    private Integer notificationReadUnread;

    private Date createTime;

    public Integer getNotificationId() {
        return notificationId;
    }

    public void setNotificationId(Integer notificationId) {
        this.notificationId = notificationId;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public String getOpenId() {
        return openId;
    }

    public void setOpenId(String openId) {
        this.openId = openId == null ? null : openId.trim();
    }

    public Integer getTemplateId() {
        return templateId;
    }

    public void setTemplateId(Integer templateId) {
        this.templateId = templateId;
    }

    public String getNotificationType() {
        return notificationType;
    }

    public void setNotificationType(String notificationType) {
        this.notificationType = notificationType == null ? null : notificationType.trim();
    }

    public String getNotificationContext() {
        return notificationContext;
    }

    public void setNotificationContext(String notificationContext) {
    	Matcher matcher = ContextConstant.EMOJI.matcher(notificationContext);    
    	
    	notificationContext = matcher.replaceAll("");  
        //2018-03-13过滤特殊字
    	notificationContext = notificationContext.replaceAll("[^(a-zA-Z0-9\\u4e00-\\u9fa5)]", "");
        
        this.notificationContext = notificationContext == null ? null : notificationContext.trim();
    }

    public Integer getNotificationReadUnread() {
        return notificationReadUnread;
    }

    public void setNotificationReadUnread(Integer notificationReadUnread) {
        this.notificationReadUnread = notificationReadUnread;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }
}