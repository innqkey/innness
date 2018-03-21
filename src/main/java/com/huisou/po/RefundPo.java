package com.huisou.po;

import java.util.Date;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
@Table(name = "busi_refund")
public class RefundPo {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer refundId;
	//退款金额
    private Long returnAmount;
    //返回退款成功后的提醒的表的id
    private Integer refundNotifyId;
    //验证的手机号
    private String validatePhone;
    //申请的微信的信息连接
    private Integer applyId;
    //用户的id
    private Integer userId;
    //退款的单号
    private String refundNo;
    //订单的id
    private Integer orderId;
    //退款的状态1.是创建 申请成功 2. 微信申请成功 3.退款ch
    private Integer refundStatus;

    private Date createTime;

    private Date updateTime;

    public Integer getRefundId() {
        return refundId;
    }

    public void setRefundId(Integer refundId) {
        this.refundId = refundId;
    }

    public Long getReturnAmount() {
        return returnAmount;
    }

    public void setReturnAmount(Long returnAmount) {
        this.returnAmount = returnAmount;
    }

    public Integer getRefundNotifyId() {
        return refundNotifyId;
    }

    public void setRefundNotifyId(Integer refundNotifyId) {
        this.refundNotifyId = refundNotifyId;
    }

    public String getValidatePhone() {
        return validatePhone;
    }

    public void setValidatePhone(String validatePhone) {
        this.validatePhone = validatePhone == null ? null : validatePhone.trim();
    }

    public Integer getApplyId() {
        return applyId;
    }

    public void setApplyId(Integer applyId) {
        this.applyId = applyId;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public String getRefundNo() {
        return refundNo;
    }

    public void setRefundNo(String refundNo) {
        this.refundNo = refundNo == null ? null : refundNo.trim();
    }

    public Integer getOrderId() {
        return orderId;
    }

    public void setOrderId(Integer orderId) {
        this.orderId = orderId;
    }

    public Integer getRefundStatus() {
        return refundStatus;
    }

    public void setRefundStatus(Integer refundStatus) {
        this.refundStatus = refundStatus;
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
}