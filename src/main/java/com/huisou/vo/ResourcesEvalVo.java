package com.huisou.vo;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
public class ResourcesEvalVo implements Serializable{
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private Integer evalId;

    private String evalContext;

    private Integer resId;

    private String resType;

    private Integer userId;
    private Integer evalLevel;

    private Date createTime;

    private String evalStatus;
    
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

	public Integer getEvalId() {
        return evalId;
    }

    public void setEvalId(Integer evalId) {
        this.evalId = evalId;
    }

    public String getEvalContext() {
        return evalContext;
    }

    public void setEvalContext(String evalContext) {
        this.evalContext = evalContext == null ? null : evalContext.trim();
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
    
    public Integer getEvalLevel() {
		return evalLevel;
	}

	public void setEvalLevel(Integer evalLevel) {
		this.evalLevel = evalLevel;
	}

	public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }


    public String getEvalStatus() {
        return evalStatus;
    }

    public void setEvalStatus(String evalStatus) {
        this.evalStatus = evalStatus == null ? null : evalStatus.trim();
    }

}