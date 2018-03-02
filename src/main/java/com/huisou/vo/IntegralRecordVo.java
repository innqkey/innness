package com.huisou.vo;

import java.io.Serializable;
import java.util.Date;

/** 
* @author 作者 :yuhao 
* @version 创建时间：2018年1月31日 下午7:27:05 
* 类说明 
*/
public class IntegralRecordVo implements Serializable{
	 	private Integer integralRecordId;

	    private Integer resId;

	    private String resTitle;

	    private Long resPrice;

	    private String resType;

	    private Integer userId;

	    private Date createTime;

	    private String payStatus;
	    
	    private String nickname;
	    
	    private String userName;
	    private String phone;
	    
		public String getUserName() {
			return userName;
		}

		public void setUserName(String userName) {
			this.userName = userName;
		}

		public Integer getIntegralRecordId() {
			return integralRecordId;
		}

		public void setIntegralRecordId(Integer integralRecordId) {
			this.integralRecordId = integralRecordId;
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

		public Long getResPrice() {
			return resPrice;
		}

		public void setResPrice(Long resPrice) {
			this.resPrice = resPrice;
		}

		public String getResType() {
			return resType;
		}

		public void setResType(String resType) {
			this.resType = resType;
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

		public String getPhone() {
			return phone;
		}

		public void setPhone(String phone) {
			this.phone = phone;
		}
	    
	    
}
