package com.huisou.po;

import java.util.Date;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
@Table(name = "busi_award_set")
public class AwardSetPo {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer awardSetId;

    private Long awardSetMoney;

    private Integer createBy;

    private Date createTime;

    private Integer updateBy;

    private Date updateTime;

    private String awardSetStatus;

    private String standby1;

    private String standby2;

    public Integer getAwardSetId() {
        return awardSetId;
    }

    public void setAwardSetId(Integer awardSetId) {
        this.awardSetId = awardSetId;
    }

    public Long getAwardSetMoney() {
        return awardSetMoney;
    }

    public void setAwardSetMoney(Long awardSetMoney) {
        this.awardSetMoney = awardSetMoney;
    }

    public Integer getCreateBy() {
        return createBy;
    }

    public void setCreateBy(Integer createBy) {
        this.createBy = createBy;
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

    public String getAwardSetStatus() {
        return awardSetStatus;
    }

    public void setAwardSetStatus(String awardSetStatus) {
        this.awardSetStatus = awardSetStatus == null ? null : awardSetStatus.trim();
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