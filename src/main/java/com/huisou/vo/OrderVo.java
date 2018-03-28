package com.huisou.vo;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import com.huisou.constant.DictConConstant;
import com.huisou.po.RegistPo;

public class OrderVo {

	 private Integer orderId;

    private String orderNo;

    private Integer resId;

    private String resType;

    private BigDecimal orderPay;

    private String payStatus;
    private String payStatusName;

	private Integer userId;

    private String phone;

    private Integer orderPersonNum;

    private Date createTime;

    private Date updateTime;

    private String orderStatus;

    private String openid;

    private String nickname;

    private String sex;

    private String province;

    private String city;

    private String country;

    private String headimgurl;

    private Long integralNum;

    private String authStatus;

    private String cardType;

    private String cardNum;

    private String resTitle;

    private BigDecimal coursePrice;

    private List<RegistPo> list;
    
    private String courseLogo;
    
    private String userName;
    
    private Integer classmateId;
    private String courseApplicants;
    private String classmateName;
    private String classmatePhone;
    
	public Integer getClassmateId() {
		return classmateId;
	}
	public void setClassmateId(Integer classmateId) {
		this.classmateId = classmateId;
	}
	public String getClassmateName() {
		return classmateName;
	}
	public void setClassmateName(String classmateName) {
		this.classmateName = classmateName;
	}
	public String getClassmatePhone() {
		return classmatePhone;
	}
	public void setClassmatePhone(String classmatePhone) {
		this.classmatePhone = classmatePhone;
	}
	public String getCourseApplicants() {
		return courseApplicants;
	}
	public void setCourseApplicants(String courseApplicants) {
		this.courseApplicants = courseApplicants;
	}
    
	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getCourseLogo() {
		return courseLogo;
	}

	public void setCourseLogo(String courseLogo) {
		this.courseLogo = courseLogo;
	}

	public List<RegistPo> getList() {
		return list;
	}

	public void setList(List<RegistPo> list) {
		this.list = list;
	}

	public Integer getOrderId() {
		return orderId;
	}

	public void setOrderId(Integer orderId) {
		this.orderId = orderId;
	}

	public String getOrderNo() {
		return orderNo;
	}

	public void setOrderNo(String orderNo) {
		this.orderNo = orderNo;
	}

	public Integer getResId() {
		return resId;
	}

	public void setResId(Integer resId) {
		this.resId = resId;
	}

	public String getResType() {
		return resType;
	}

	public void setResType(String resType) {
		this.resType = resType;
	}

	public BigDecimal getOrderPay() {
		return orderPay;
	}

	public void setOrderPay(BigDecimal orderPay) {
		this.orderPay = orderPay;
	}

	public String getPayStatus() {
		return payStatus;
	}

	public void setPayStatus(String payStatus) {
		this.payStatus = payStatus;
	}

	public Integer getUserId() {
		return userId;
	}

	public void setUserId(Integer userId) {
		this.userId = userId;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
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

	public Date getUpdateTime() {
		return updateTime;
	}

	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
	}

	public String getOrderStatus() {
		return orderStatus;
	}

	public void setOrderStatus(String orderStatus) {
		this.orderStatus = orderStatus;
	}

	public String getOpenid() {
		return openid;
	}

	public void setOpenid(String openid) {
		this.openid = openid;
	}

	public String getNickname() {
		return nickname;
	}

	public void setNickname(String nickname) {
		this.nickname = nickname;
	}

	public String getSex() {
		return sex;
	}

	public void setSex(String sex) {
		this.sex = sex;
	}

	public String getProvince() {
		return province;
	}

	public void setProvince(String province) {
		this.province = province;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	public String getHeadimgurl() {
		return headimgurl;
	}

	public void setHeadimgurl(String headimgurl) {
		this.headimgurl = headimgurl;
	}

	public Long getIntegralNum() {
		return integralNum;
	}

	public void setIntegralNum(Long integralNum) {
		this.integralNum = integralNum;
	}

	public String getAuthStatus() {
		return authStatus;
	}

	public void setAuthStatus(String authStatus) {
		this.authStatus = authStatus;
	}

	public String getCardType() {
		return cardType;
	}

	public void setCardType(String cardType) {
		this.cardType = cardType;
	}

	public String getCardNum() {
		return cardNum;
	}

	public void setCardNum(String cardNum) {
		this.cardNum = cardNum;
	}

	public String getResTitle() {
		return resTitle;
	}

	public void setResTitle(String resTitle) {
		this.resTitle = resTitle;
	}

	public BigDecimal getCoursePrice() {
		return coursePrice;
	}

	public void setCoursePrice(BigDecimal coursePrice) {
		this.coursePrice = coursePrice;
	}
    
	public String getPayStatusName() {
    	return DictConConstant.getDicName("payStatus",this.payStatus);
	}

	public void setPayStatusName(String payStatusName) {
		this.payStatusName = payStatusName;
	}

}
