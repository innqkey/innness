package com.huisou.vo;

import java.util.Date;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
public class ResourcesThumbsVo {
    private Integer thumbsId;

    private Integer resId;

    private String resType;

    private Integer userId;
    private String thumbsStatus;

    private Date createTime;
    private String nickName;
    private String headimgUrl;
    private String userName;
    
    public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getNickName() {
		return nickName;
	}

	public void setNickName(String nickName) {
		this.nickName = nickName;
	}

	public String getHeadimgUrl() {
		return headimgUrl;
	}

	public void setHeadimgUrl(String headimgUrl) {
		this.headimgUrl = headimgUrl;
	}

	public String getThumbsStatus() {
		return thumbsStatus;
	}

	public void setThumbsStatus(String thumbsStatus) {
		this.thumbsStatus = thumbsStatus;
	}

	public Integer getThumbsId() {
        return thumbsId;
    }

    public void setThumbsId(Integer thumbsId) {
        this.thumbsId = thumbsId;
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
        this.resType = resType == null ? null : resType.trim();
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

}