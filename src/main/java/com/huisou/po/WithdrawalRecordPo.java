package com.huisou.po;

import java.util.Date;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
@Table(name = "busi_withdrawal_record")
public class WithdrawalRecordPo {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer withdrawalRecordId;

    private Integer userBankId;

    private Integer userId;

    private Long withdrawAccount;

    private String userBankAccount;

    private String userBankName;

    private String userBankType;

    private String recoreStatus;

    private Date createTime;

    private Integer auditBy;

    private Date updateTime;

    private String standby1;

    private String standby2;

    public Integer getWithdrawalRecordId() {
        return withdrawalRecordId;
    }

    public void setWithdrawalRecordId(Integer withdrawalRecordId) {
        this.withdrawalRecordId = withdrawalRecordId;
    }

    public Integer getUserBankId() {
        return userBankId;
    }

    public void setUserBankId(Integer userBankId) {
        this.userBankId = userBankId;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public Long getWithdrawAccount() {
        return withdrawAccount;
    }

    public void setWithdrawAccount(Long withdrawAccount) {
        this.withdrawAccount = withdrawAccount;
    }

    public String getUserBankAccount() {
        return userBankAccount;
    }

    public void setUserBankAccount(String userBankAccount) {
        this.userBankAccount = userBankAccount == null ? null : userBankAccount.trim();
    }

    public String getUserBankName() {
        return userBankName;
    }

    public void setUserBankName(String userBankName) {
        this.userBankName = userBankName == null ? null : userBankName.trim();
    }

    public String getUserBankType() {
        return userBankType;
    }

    public void setUserBankType(String userBankType) {
        this.userBankType = userBankType == null ? null : userBankType.trim();
    }

    public String getRecoreStatus() {
        return recoreStatus;
    }

    public void setRecoreStatus(String recoreStatus) {
        this.recoreStatus = recoreStatus == null ? null : recoreStatus.trim();
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Integer getAuditBy() {
        return auditBy;
    }

    public void setAuditBy(Integer auditBy) {
        this.auditBy = auditBy;
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