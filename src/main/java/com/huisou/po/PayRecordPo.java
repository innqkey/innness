package com.huisou.po;

import java.math.BigDecimal;
import java.util.Date;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
@Table(name = "busi_pay_record")
public class PayRecordPo {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer payRecordId;

    private Integer orderId;

    private Integer resId;

    private String resTitle;

    private BigDecimal resPrice;

    private String resType;

    private BigDecimal payMoney;

    private String weixinRecord;

    private Integer userId;

    private Date createTime;

    private Date updateTime;

    private String payStatus;

    private String payFailCause;

    private String standby2;

    public Integer getPayRecordId() {
        return payRecordId;
    }

    public void setPayRecordId(Integer payRecordId) {
        this.payRecordId = payRecordId;
    }

    public Integer getOrderId() {
        return orderId;
    }

    public void setOrderId(Integer orderId) {
        this.orderId = orderId;
    }

    public Integer getResId() {
        return resId;
    }

    public void setResId(Integer resId) {
        this.resId = resId;
    }

    public String getResTitle() {
        return resTitle;
    }

    public void setResTitle(String resTitle) {
        this.resTitle = resTitle == null ? null : resTitle.trim();
    }

    public BigDecimal getResPrice() {
        return resPrice;
    }

    public void setResPrice(BigDecimal resPrice) {
        this.resPrice = resPrice;
    }

    public String getResType() {
        return resType;
    }

    public void setResType(String resType) {
        this.resType = resType == null ? null : resType.trim();
    }

    public BigDecimal getPayMoney() {
        return payMoney;
    }

    public void setPayMoney(BigDecimal payMoney) {
        this.payMoney = payMoney;
    }

    public String getWeixinRecord() {
        return weixinRecord;
    }

    public void setWeixinRecord(String weixinRecord) {
        this.weixinRecord = weixinRecord == null ? null : weixinRecord.trim();
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

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    public String getPayStatus() {
        return payStatus;
    }

    public void setPayStatus(String payStatus) {
        this.payStatus = payStatus == null ? null : payStatus.trim();
    }

    public String getPayFailCause() {
        return payFailCause;
    }

    public void setPayFailCause(String payFailCause) {
        this.payFailCause = payFailCause == null ? null : payFailCause.trim();
    }

    public String getStandby2() {
        return standby2;
    }

    public void setStandby2(String standby2) {
        this.standby2 = standby2 == null ? null : standby2.trim();
    }
}