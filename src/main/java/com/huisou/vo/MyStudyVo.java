package com.huisou.vo;

import java.util.Date;
public class MyStudyVo {
    private Integer myStudyId;

    private Integer resId;

    private String resUrl;
    
    private String resTitle;

    private String resType;

    private Integer userId;

    private Date createTime;
    private Integer materialDownNum;
    
    public Integer getMaterialDownNum() {
		return materialDownNum;
	}

	public void setMaterialDownNum(Integer materialDownNum) {
		this.materialDownNum = materialDownNum;
	}

	public String getResUrl() {
		return resUrl;
	}

	public void setResUrl(String resUrl) {
		this.resUrl = resUrl;
	}

	public Integer getMyStudyId() {
        return myStudyId;
    }

    public void setMyStudyId(Integer myStudyId) {
        this.myStudyId = myStudyId;
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
        this.resTitle = resTitle == null ? null : resTitle.trim();
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