package com.huisou.po;

import java.util.Date;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Table(name = "busi_award_record")
public class AwardRecordPo {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer awardRecordId;

    private String awardNo;

    private Long awardMoney;

    private Date createTime;

    private Integer resId;

    private String resType;

    private Integer userId;

    private String awardStatus;

    private String standby1;

    private String standby2;

    public Integer getAwardRecordId() {
        return awardRecordId;
    }

    public void setAwardRecordId(Integer awardRecordId) {
        this.awardRecordId = awardRecordId;
    }

    public String getAwardNo() {
        return awardNo;
    }

    public void setAwardNo(String awardNo) {
        this.awardNo = awardNo == null ? null : awardNo.trim();
    }

    public Long getAwardMoney() {
        return awardMoney;
    }

    public void setAwardMoney(Long awardMoney) {
        this.awardMoney = awardMoney;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
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

    public String getAwardStatus() {
        return awardStatus;
    }

    public void setAwardStatus(String awardStatus) {
        this.awardStatus = awardStatus == null ? null : awardStatus.trim();
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