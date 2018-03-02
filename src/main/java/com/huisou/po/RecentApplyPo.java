package com.huisou.po;

import java.util.Date;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
@Table(name = "busi_recent_apply")
public class RecentApplyPo {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer recentApplyId;

    private Integer recentCourseId;

    private Integer userId;

    private Integer orderId;

    private Integer registId;

    private Date createTime;

    private Date updateTime;

    private String recentCourseStatus;

    private String standby1;

    private String standby2;

    public Integer getRecentApplyId() {
        return recentApplyId;
    }

    public void setRecentApplyId(Integer recentApplyId) {
        this.recentApplyId = recentApplyId;
    }

    public Integer getRecentCourseId() {
        return recentCourseId;
    }

    public void setRecentCourseId(Integer recentCourseId) {
        this.recentCourseId = recentCourseId;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public Integer getOrderId() {
        return orderId;
    }

    public void setOrderId(Integer orderId) {
        this.orderId = orderId;
    }

    public Integer getRegistId() {
        return registId;
    }

    public void setRegistId(Integer registId) {
        this.registId = registId;
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

    public String getRecentCourseStatus() {
        return recentCourseStatus;
    }

    public void setRecentCourseStatus(String recentCourseStatus) {
        this.recentCourseStatus = recentCourseStatus == null ? null : recentCourseStatus.trim();
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