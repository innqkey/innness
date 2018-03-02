package com.huisou.vo;

import java.io.Serializable;
import java.util.Date;

import com.huisou.constant.DictConConstant;

public class UserVo implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

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

    public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

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
        this.openid = openid == null ? null : openid.trim();
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname == null ? null : nickname.trim();
    }

    public String getHeadimgurl() {
        return headimgurl;
    }

    public void setHeadimgurl(String headimgurl) {
        this.headimgurl = headimgurl == null ? null : headimgurl.trim();
    }

    public Long getIntegralNum() {
        return integralNum;
    }

    public void setIntegralNum(Long integralNum) {
        this.integralNum = integralNum;
    }

    public String getAuthStatus() {
        return DictConConstant.getDicName("authStatus", authStatus);
    }

    public void setAuthStatus(String authStatus) {
        this.authStatus = authStatus == null ? null : authStatus.trim();
    }

    public String getCardType() {
        return cardType;
    }

    public void setCardType(String cardType) {
        this.cardType = cardType == null ? null : cardType.trim();
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone == null ? null : phone.trim();
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

}