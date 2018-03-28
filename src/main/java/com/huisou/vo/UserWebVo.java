package com.huisou.vo;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import com.huisou.po.CoursePo;

/** 
* @author 作者 :yuhao 
* @version 创建时间：2018年3月26日 上午9:37:44 
* 类说明 
*/
public class UserWebVo implements Serializable{
	 private Integer userId;

	    private String openid;

	    private String nickname;

	    private String headimgurl;
	    private String username;

	    private Long integralNum;

	    private String authStatus;

	    private String cardType;

	    private String phone;

	    private Date createTime;
	    
	    private String sex;

	    private String province;

	    private String city;

	    private String country;

	    private String userStatus;

	    private String cardNum;

	    private String standby1;
	    
	    private Integer classmateUserId;

	    private String standby2;
	    
	    private List<CoursePo> list;
	    
	    private String isApply;
	    
	    private Integer memberSetId;
	    
	    private String isAgency;
	    
	    private String memberSetName;

		public Integer getUserId() {
			return userId;
		}

		public void setUserId(Integer userId) {
			this.userId = userId;
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

		public String getHeadimgurl() {
			return headimgurl;
		}

		public void setHeadimgurl(String headimgurl) {
			this.headimgurl = headimgurl;
		}

		public String getUsername() {
			return username;
		}

		public void setUsername(String username) {
			this.username = username;
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

		public String getPhone() {
			return phone;
		}

		public void setPhone(String phone) {
			this.phone = phone;
		}

		public Date getCreateTime() {
			return createTime;
		}

		public void setCreateTime(Date createTime) {
			this.createTime = createTime;
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

		public String getUserStatus() {
			return userStatus;
		}

		public void setUserStatus(String userStatus) {
			this.userStatus = userStatus;
		}

		public String getCardNum() {
			return cardNum;
		}

		public void setCardNum(String cardNum) {
			this.cardNum = cardNum;
		}

		public String getStandby1() {
			return standby1;
		}

		public void setStandby1(String standby1) {
			this.standby1 = standby1;
		}

		public Integer getClassmateUserId() {
			return classmateUserId;
		}

		public void setClassmateUserId(Integer classmateUserId) {
			this.classmateUserId = classmateUserId;
		}

		public String getStandby2() {
			return standby2;
		}

		public void setStandby2(String standby2) {
			this.standby2 = standby2;
		}

		public List<CoursePo> getList() {
			return list;
		}

		public void setList(List<CoursePo> list) {
			this.list = list;
		}

		public String getIsApply() {
			return isApply;
		}

		public void setIsApply(String isApply) {
			this.isApply = isApply;
		}

		public Integer getMemberSetId() {
			return memberSetId;
		}

		public void setMemberSetId(Integer memberSetId) {
			this.memberSetId = memberSetId;
		}

		public String getIsAgency() {
			return isAgency;
		}

		public void setIsAgency(String isAgency) {
			this.isAgency = isAgency;
		}

		public String getMemberSetName() {
			return memberSetName;
		}

		public void setMemberSetName(String memberSetName) {
			this.memberSetName = memberSetName;
		}
	    
	    
}
