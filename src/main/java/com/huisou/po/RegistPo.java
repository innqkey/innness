package com.huisou.po;

import java.util.Date;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
@Table(name = "busi_regist")
public class RegistPo {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer registId;

    private Integer userId;

    private String registName;

    private String cardType;

    private String cardTypeName;

    private String cardNum;

    private String cardPhone;

    private Date createTime;

    private Date updateTime;

    private String registStatus;

    private String standby1;

    private String standby2;

    public Integer getRegistId() {
        return registId;
    }

    public void setRegistId(Integer registId) {
        this.registId = registId;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public String getRegistName() {
        return registName;
    }

    public void setRegistName(String registName) {
        this.registName = registName == null ? null : registName.trim();
    }

    public String getCardType() {
        return cardType;
    }

    public void setCardType(String cardType) {
        this.cardType = cardType == null ? null : cardType.trim();
    }

    public String getCardTypeName() {
        return cardTypeName;
    }

    public void setCardTypeName(String cardTypeName) {
        this.cardTypeName = cardTypeName == null ? null : cardTypeName.trim();
    }

    public String getCardNum() {
        return cardNum;
    }

    public void setCardNum(String cardNum) {
        this.cardNum = cardNum == null ? null : cardNum.trim();
    }

    public String getCardPhone() {
        return cardPhone;
    }

    public void setCardPhone(String cardPhone) {
        this.cardPhone = cardPhone == null ? null : cardPhone.trim();
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

    public String getRegistStatus() {
        return registStatus;
    }

    public void setRegistStatus(String registStatus) {
        this.registStatus = registStatus == null ? null : registStatus.trim();
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