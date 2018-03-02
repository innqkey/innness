package com.huisou.vo;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

/** 
* @author 作者 :yuhao 
* @version 创建时间：2018年1月31日 下午1:55:34 
* 类说明 
*/
public class PayRecordVo implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1224131883064296276L;
	
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

    private String payStatus;
    
    private String nickname;
    
    private String phone;
    

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

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
		this.resTitle = resTitle;
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
		this.resType = resType;
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
		this.weixinRecord = weixinRecord;
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

	public String getPayStatus() {
		return payStatus;
	}

	public void setPayStatus(String payStatus) {
		this.payStatus = payStatus;
	}

	public String getNickname() {
		return nickname;
	}

	public void setNickname(String nickname) {
		this.nickname = nickname;
	}
    
    
}
