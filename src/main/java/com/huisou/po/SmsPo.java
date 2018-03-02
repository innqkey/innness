package com.huisou.po;

import java.util.Date;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
@Table(name = "busi_sms")
public class SmsPo {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer smsId;

    private String smsCode;

    private String smsLong;

    private Integer userId;

    private String phone;

    private String smsSendStatus;

    private String smsPlatform;

    private Date createTime;

    private Date updateTime;

    private String standby1;

    private String standby2;

    public Integer getSmsId() {
        return smsId;
    }

    public void setSmsId(Integer smsId) {
        this.smsId = smsId;
    }

    public String getSmsCode() {
        return smsCode;
    }

    public void setSmsCode(String smsCode) {
        this.smsCode = smsCode == null ? null : smsCode.trim();
    }

    public String getSmsLong() {
        return smsLong;
    }

    public void setSmsLong(String smsLong) {
        this.smsLong = smsLong == null ? null : smsLong.trim();
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone == null ? null : phone.trim();
    }

    public String getSmsSendStatus() {
        return smsSendStatus;
    }

    public void setSmsSendStatus(String smsSendStatus) {
        this.smsSendStatus = smsSendStatus == null ? null : smsSendStatus.trim();
    }

    public String getSmsPlatform() {
        return smsPlatform;
    }

    public void setSmsPlatform(String smsPlatform) {
        this.smsPlatform = smsPlatform == null ? null : smsPlatform.trim();
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

    public String getStandby1() {
        return standby1;
    }

    public void setStandby1(String standby1) {
        this.standby1 = standby1 == null ? null : standby1.trim();
    }

    public String getStandby2() {
        return standby2;
    }

    public void setStandby2(String standby2) {
        this.standby2 = standby2 == null ? null : standby2.trim();
    }
}