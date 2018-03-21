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
		private Integer courseId;

	    private String courseTitle;

	    private BigDecimal coursePrice;

	    private String courseIntro;

	    private String courseSpeaker;

	    private String courseDuration;

	    private String introvideoUrl;

	    private String coursePicture;

	    private String courseLogo;
	    
	    private String courseStatus;
	    
	    private String courseDetail;
	    
	    private Integer userId;

	    private String nickname;
	    
	    private String username;
	    
	    private Integer orderId;
	    
	    private String payStatus;
	    
	    private BigDecimal orderPay;
	    
	    private Integer orderPersonNum;
	    
	    private Date createTime;

		public Integer getCourseId() {
			return courseId;
		}

		public void setCourseId(Integer courseId) {
			this.courseId = courseId;
		}

		public String getCourseTitle() {
			return courseTitle;
		}

		public void setCourseTitle(String courseTitle) {
			this.courseTitle = courseTitle;
		}

		public BigDecimal getCoursePrice() {
			return coursePrice;
		}

		public void setCoursePrice(BigDecimal coursePrice) {
			this.coursePrice = coursePrice;
		}

		public String getCourseIntro() {
			return courseIntro;
		}

		public void setCourseIntro(String courseIntro) {
			this.courseIntro = courseIntro;
		}

		public String getCourseSpeaker() {
			return courseSpeaker;
		}

		public void setCourseSpeaker(String courseSpeaker) {
			this.courseSpeaker = courseSpeaker;
		}

		public String getCourseDuration() {
			return courseDuration;
		}

		public void setCourseDuration(String courseDuration) {
			this.courseDuration = courseDuration;
		}

		public String getIntrovideoUrl() {
			return introvideoUrl;
		}

		public void setIntrovideoUrl(String introvideoUrl) {
			this.introvideoUrl = introvideoUrl;
		}

		public String getCoursePicture() {
			return coursePicture;
		}

		public void setCoursePicture(String coursePicture) {
			this.coursePicture = coursePicture;
		}

		public String getCourseLogo() {
			return courseLogo;
		}

		public void setCourseLogo(String courseLogo) {
			this.courseLogo = courseLogo;
		}

		public String getCourseStatus() {
			return courseStatus;
		}

		public void setCourseStatus(String courseStatus) {
			this.courseStatus = courseStatus;
		}

		public String getCourseDetail() {
			return courseDetail;
		}

		public void setCourseDetail(String courseDetail) {
			this.courseDetail = courseDetail;
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
	    
	    
	    
}
