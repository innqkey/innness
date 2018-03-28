package com.huisou.po;

import java.util.Date;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
@Table(name = "busi_user_account")
public class UserAccountPo {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer userAccountId;

    private Integer userId;
    /**
     * 一共获取的所有的金额
     */
    private Long accountAllMoney;
    /**
     * 待提现金额
     */
    private Long withdrawMoney;
    
    /**
     * 剩余提现金额
     */
    private Long accountMoney;
    
    /**
     * 已经提现金额
     */
    private Long withdrawOverMoney;

    private String bankAccount;

    private Integer createBy;

    private Date createTime;

    public Integer getUserAccountId() {
        return userAccountId;
    }

    public void setUserAccountId(Integer userAccountId) {
        this.userAccountId = userAccountId;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public Long getAccountAllMoney() {
        return accountAllMoney;
    }

    public void setAccountAllMoney(Long accountAllMoney) {
        this.accountAllMoney = accountAllMoney;
    }

    public Long getWithdrawMoney() {
        return withdrawMoney;
    }

    public void setWithdrawMoney(Long withdrawMoney) {
        this.withdrawMoney = withdrawMoney;
    }

    public Long getAccountMoney() {
        return accountMoney;
    }

    public void setAccountMoney(Long accountMoney) {
        this.accountMoney = accountMoney;
    }

    public Long getWithdrawOverMoney() {
        return withdrawOverMoney;
    }

    public void setWithdrawOverMoney(Long withdrawOverMoney) {
        this.withdrawOverMoney = withdrawOverMoney;
    }

    public String getBankAccount() {
        return bankAccount;
    }

    public void setBankAccount(String bankAccount) {
        this.bankAccount = bankAccount == null ? null : bankAccount.trim();
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
}