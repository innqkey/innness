package com.huisou.po;

import java.util.Date;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
@Table(name = "busi_resources_eval")
public class ResourcesEvalPo {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer evalId;

    private String evalContext;

    private Integer evalLevel;

    private Integer resId;

    private String resType;

    private Integer userId;

    private Date createTime;

    private Integer updateBy;

    private Date updateTime;

    private String evalStatus;

    private String standby1;

    private String standby2;

    public Integer getEvalId() {
        return evalId;
    }

    public void setEvalId(Integer evalId) {
        this.evalId = evalId;
    }

    public String getEvalContext() {
        return evalContext;
    }

    public void setEvalContext(String evalContext) {
        this.evalContext = evalContext == null ? null : evalContext.trim();
    }

    public Integer getEvalLevel() {
        return evalLevel;
    }

    public void setEvalLevel(Integer evalLevel) {
        this.evalLevel = evalLevel;
    }

    public Integer getResId() {
        return resId;
    }

    public void setResId(Integer resId) {
        this.resId = resId;
    }

    public String getResType() {
        return resType;
    }

    public void setResType(String resType) {
        this.resType = resType == null ? null : resType.trim();
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Integer getUpdateBy() {
        return updateBy;
    }

    public void setUpdateBy(Integer updateBy) {
        this.updateBy = updateBy;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    public String getEvalStatus() {
        return evalStatus;
    }

    public void setEvalStatus(String evalStatus) {
        this.evalStatus = evalStatus == null ? null : evalStatus.trim();
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