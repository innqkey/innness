package com.huisou.vo;

import java.io.Serializable;
import java.util.Date;

/** 
* @author 作者 :yuhao 
* @version 创建时间：2018年1月30日 下午7:45:02 
* 类说明 
*/
public class RecentApplyVo implements Serializable{
	    private Integer recentApplyId;

	    private Integer recentCourseId;

	    private Integer userId;

	    private Integer orderId;

	    private Integer registId;

	    private String cardType;

	    private String cardTypeName;

	    private String cardNum;

	    private String cardPhone;
	    
	    /**
	     * 报名时间
	     */
	    private Date createTime;
	    
	    private String headimgurl;

	    private String nickname;

	    
	    
		public String getHeadimgurl() {
			return headimgurl;
		}

		public void setHeadimgurl(String headimgurl) {
			this.headimgurl = headimgurl;
		}

		
		public String getNickname() {
			return nickname;
		}

		public void setNickname(String nickname) {
			this.nickname = nickname;
		}

		public Integer getRecentApplyId() {
			return recentApplyId;
		}

		public void setRecentApplyId(Integer recentApplyId) {
			this.recentApplyId = recentApplyId;
		}

		public Integer getRecentCourseId() {
			return recentCourseId;
		}

		public void setRecentCourseId(Integer recentCourseId) {
			this.recentCourseId = recentCourseId;
		}

		public Integer getUserId() {
			return userId;
		}

		public void setUserId(Integer userId) {
			this.userId = userId;
		}

		public Integer getOrderId() {
			return orderId;
		}

		public void setOrderId(Integer orderId) {
			this.orderId = orderId;
		}

		public Integer getRegistId() {
			return registId;
		}

		public void setRegistId(Integer registId) {
			this.registId = registId;
		}

		public String getCardType() {
			return cardType;
		}

		public void setCardType(String cardType) {
			this.cardType = cardType;
		}

		public String getCardTypeName() {
			return cardTypeName;
		}

		public void setCardTypeName(String cardTypeName) {
			this.cardTypeName = cardTypeName;
		}

		public String getCardNum() {
			return cardNum;
		}

		public void setCardNum(String cardNum) {
			this.cardNum = cardNum;
		}

		public String getCardPhone() {
			return cardPhone;
		}

		public void setCardPhone(String cardPhone) {
			this.cardPhone = cardPhone;
		}

		public Date getCreateTime() {
			return createTime;
		}

		public void setCreateTime(Date createTime) {
			this.createTime = createTime;
		}
	    
}
