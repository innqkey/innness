package com.huisou.po;

import java.util.Date;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
@Table(name = "busi_member_set")
public class MemberSetPo {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer memberSetId;

    private String memberSetName;

    private Long memberSetMoney;

    private Integer createBy;

    private Date createTime;

    private String memberSetStatus;

    private String standby1;

    private String standby2;

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

    public Long getMemberSetMoney() {
        return memberSetMoney;
    }

    public void setMemberSetMoney(Long memberSetMoney) {
        this.memberSetMoney = memberSetMoney;
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

    public String getMemberSetStatus() {
        return memberSetStatus;
    }

    public void setMemberSetStatus(String memberSetStatus) {
        this.memberSetStatus = memberSetStatus == null ? null : memberSetStatus.trim();
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