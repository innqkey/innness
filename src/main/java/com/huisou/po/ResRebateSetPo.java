package com.huisou.po;

import java.util.Date;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
@Table(name = "busi_res_rebate_set")
public class ResRebateSetPo {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer resRebateId;

    private Integer resId;

    private String resType;

    private Long resRebateMoney;
    
    private Integer memberSetId;

    private String memberSetName;

    private Integer createBy;

    private Date createTime;

    private Integer updateBy;

    private Date updateTime;

    private String standby1;

    private String standby2;

    public Integer getResRebateId() {
        return resRebateId;
    }

    public void setResRebateId(Integer resRebateId) {
        this.resRebateId = resRebateId;
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

    public Long getResRebateMoney() {
		return resRebateMoney;
	}

	public void setResRebateMoney(Long resRebateMoney) {
		this.resRebateMoney = resRebateMoney;
	}

	public Integer getMemberSetId() {
        return memberSetId;
    }

    public void setMemberSetId(Integer memberSetId) {
        this.memberSetId = memberSetId;
    }

    public String getMemberSetName() {
        return memberSetName;
    }

    public void setMemberSetName(String memberSetName) {
        this.memberSetName = memberSetName == null ? null : memberSetName.trim();
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