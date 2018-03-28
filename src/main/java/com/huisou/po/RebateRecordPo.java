package com.huisou.po;

import java.util.Date;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
@Table(name = "busi_rebate_record")
public class RebateRecordPo {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer rebateRecordId;

    private Integer resOrderId;

    private String resOrderType;

    private String resOrderName;

    private String resOrderNo;

    private Integer buyUserId;

    private Integer rebateUserId;

    private Long orderRebateMoney;

    private Date createTime;

    private String standby1;

    private String standby2;

    public Integer getRebateRecordId() {
        return rebateRecordId;
    }

    public void setRebateRecordId(Integer rebateRecordId) {
        this.rebateRecordId = rebateRecordId;
    }

    public Integer getResOrderId() {
        return resOrderId;
    }

    public void setResOrderId(Integer resOrderId) {
        this.resOrderId = resOrderId;
    }

    public String getResOrderType() {
        return resOrderType;
    }

    public void setResOrderType(String resOrderType) {
        this.resOrderType = resOrderType == null ? null : resOrderType.trim();
    }

    public String getResOrderName() {
        return resOrderName;
    }

    public void setResOrderName(String resOrderName) {
        this.resOrderName = resOrderName == null ? null : resOrderName.trim();
    }

    public String getResOrderNo() {
        return resOrderNo;
    }

    public void setResOrderNo(String resOrderNo) {
        this.resOrderNo = resOrderNo == null ? null : resOrderNo.trim();
    }

    public Integer getBuyUserId() {
        return buyUserId;
    }

    public void setBuyUserId(Integer buyUserId) {
        this.buyUserId = buyUserId;
    }

    public Integer getRebateUserId() {
        return rebateUserId;
    }

    public void setRebateUserId(Integer rebateUserId) {
        this.rebateUserId = rebateUserId;
    }

    public Long getOrderRebateMoney() {
        return orderRebateMoney;
    }

    public void setOrderRebateMoney(Long orderRebateMoney) {
        this.orderRebateMoney = orderRebateMoney;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
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