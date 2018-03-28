package com.huisou.po;

import java.util.Date;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
@Table(name = "busi_user_bank")
public class UserBankPo {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer userBankId;

    private Integer userId;

    private String userBankAccount;

    private String userBankName;

    private String userBankBranchName;

    private String userBankType;

    private Date createTime;

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

    public String getUserBankBranchName() {
        return userBankBranchName;
    }

    public void setUserBankBranchName(String userBankBranchName) {
        this.userBankBranchName = userBankBranchName == null ? null : userBankBranchName.trim();
    }

    public String getUserBankType() {
        return userBankType;
    }

    public void setUserBankType(String userBankType) {
        this.userBankType = userBankType == null ? null : userBankType.trim();
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }
}