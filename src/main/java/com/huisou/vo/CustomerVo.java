package com.huisou.vo;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

import com.huisou.po.CoursePo;

/** 
* @author 作者 :yuhao 
* @version 创建时间：2018年3月15日 下午4:03:25 
* 类说明 
*/
public class CustomerVo implements Serializable{
		private Integer id;

	    private String title;

	    private BigDecimal price;

	    private String intro;

	    private String speaker;

	    private String duration;

	    private String introvideoUrl;

	    private String picture;

	    private String logo;
	    
	    private String Detail;
	    
	    private Integer userId;

	    private String nickname;
	    
	    private String username;
	    
	    private Integer orderId;
	    
	    private String payStatus;
	    
	    private BigDecimal orderPay;
	    
	    private Integer orderPersonNum;
	    
	    private Date createTime;

	    private Integer memberSetId;
	    
	    private String phone;
	    
	    private String isAgency;
	    
		public String getIsAgency() {
			return isAgency;
		}

		public void setIsAgency(String isAgency) {
			this.isAgency = isAgency;
		}

		public String getPhone() {
			return phone;
		}

		public void setPhone(String phone) {
			this.phone = phone;
		}

		public Integer getId() {
			return id;
		}

		public void setId(Integer id) {
			this.id = id;
		}

		public String getTitle() {
			return title;
		}

		public void setTitle(String title) {
			this.title = title;
		}

		public BigDecimal getPrice() {
			return price;
		}

		public void setPrice(BigDecimal price) {
			this.price = price;
		}

		public String getIntro() {
			return intro;
		}

		public void setIntro(String intro) {
			this.intro = intro;
		}

		public String getSpeaker() {
			return speaker;
		}

		public void setSpeaker(String speaker) {
			this.speaker = speaker;
		}

		public String getDuration() {
			return duration;
		}

		public void setDuration(String duration) {
			this.duration = duration;
		}

		public String getIntrovideoUrl() {
			return introvideoUrl;
		}

		public void setIntrovideoUrl(String introvideoUrl) {
			this.introvideoUrl = introvideoUrl;
		}

		public String getPicture() {
			return picture;
		}

		public void setPicture(String picture) {
			this.picture = picture;
		}

		public String getLogo() {
			return logo;
		}

		public void setLogo(String logo) {
			this.logo = logo;
		}

		public String getDetail() {
			return Detail;
		}

		public void setDetail(String detail) {
			Detail = detail;
		}

		public Integer getUserId() {
			return userId;
		}

		public void setUserId(Integer userId) {
			this.userId = userId;
		}

		public String getNickname() {
			return nickname;
		}

		public void setNickname(String nickname) {
			this.nickname = nickname;
		}

		public String getUsername() {
			return username;
		}

		public void setUsername(String username) {
			this.username = username;
		}

		public Integer getOrderId() {
			return orderId;
		}

		public void setOrderId(Integer orderId) {
			this.orderId = orderId;
		}

		public String getPayStatus() {
			return payStatus;
		}

		public void setPayStatus(String payStatus) {
			this.payStatus = payStatus;
		}

		public BigDecimal getOrderPay() {
			return orderPay;
		}

		public void setOrderPay(BigDecimal orderPay) {
			this.orderPay = orderPay;
		}

		public Integer getOrderPersonNum() {
			return orderPersonNum;
		}

		public void setOrderPersonNum(Integer orderPersonNum) {
			this.orderPersonNum = orderPersonNum;
		}

		public Date getCreateTime() {
			return createTime;
		}

		public void setCreateTime(Date createTime) {
			this.createTime = createTime;
		}

		public Integer getMemberSetId() {
			return memberSetId;
		}

		public void setMemberSetId(Integer memberSetId) {
			this.memberSetId = memberSetId;
		}
	    
}
