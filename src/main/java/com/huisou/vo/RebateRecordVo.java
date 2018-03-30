package com.huisou.vo;

import java.io.Serializable;
import java.util.Date;

/** 
* @author 作者 :yuhao 
* @version 创建时间：2018年3月29日 下午5:12:29 
* 类说明 
*/
public class RebateRecordVo implements Serializable{
	
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
	    
	    private String buyUserName;
	    
	    private String buyUserNickname;
	    
	    private String buyUserHeadimgurl;

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
			this.resOrderType = resOrderType;
		}

		public String getResOrderName() {
			return resOrderName;
		}

		public void setResOrderName(String resOrderName) {
			this.resOrderName = resOrderName;
		}

		public String getResOrderNo() {
			return resOrderNo;
		}

		public void setResOrderNo(String resOrderNo) {
			this.resOrderNo = resOrderNo;
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
			this.standby1 = standby1;
		}

		public String getStandby2() {
			return standby2;
		}

		public void setStandby2(String standby2) {
			this.standby2 = standby2;
		}

		public String getBuyUserName() {
			return buyUserName;
		}

		public void setBuyUserName(String buyUserName) {
			this.buyUserName = buyUserName;
		}

		public String getBuyUserNickname() {
			return buyUserNickname;
		}

		public void setBuyUserNickname(String buyUserNickname) {
			this.buyUserNickname = buyUserNickname;
		}

		public String getBuyUserHeadimgurl() {
			return buyUserHeadimgurl;
		}

		public void setBuyUserHeadimgurl(String buyUserHeadimgurl) {
			this.buyUserHeadimgurl = buyUserHeadimgurl;
		}
	    
	    
}
