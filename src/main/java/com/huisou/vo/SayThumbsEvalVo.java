package com.huisou.vo;

import java.util.Date;
import java.util.List;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
public class SayThumbsEvalVo {
    private Integer sayId;

    private String sayTitle;

    private String sayContext;

    private String sayLogo;

    private String sayHeadImg;

    private String sayName;

    private Integer sayEvalNum;

    private Integer sayThumbsNum;

    private Integer createBy;

    private Date createTime;

    private Integer updateBy;

    private Date updateTime;

    private String sayStatus;

    private String sayTitleName;
    private boolean show;
    private List<ResourcesEvalVo> evalList;
    private List<ResourcesThumbsVo> thumbsList;
    
	public boolean isShow() {
		return show;
	}

	public void setShow(boolean show) {
		this.show = show;
	}

	public List<ResourcesEvalVo> getEvalList() {
		return evalList;
	}

	public void setEvalList(List<ResourcesEvalVo> evalList) {
		this.evalList = evalList;
	}

	public List<ResourcesThumbsVo> getThumbsList() {
		return thumbsList;
	}

	public void setThumbsList(List<ResourcesThumbsVo> thumbsList) {
		this.thumbsList = thumbsList;
	}

	public String getSayTitleName() {
//		return sayTitleName;
    	return new StringBuilder("--").append(sayName).append(" ").append("《")
    			.append(sayTitle).append("》").toString();
	}

	public void setSayTitleName(String sayTitleName) {
		this.sayTitleName = sayTitleName;
	}

	public Integer getSayId() {
        return sayId;
    }

    public void setSayId(Integer sayId) {
        this.sayId = sayId;
    }

    public String getSayTitle() {
        return sayTitle;
    }

    public void setSayTitle(String sayTitle) {
        this.sayTitle = sayTitle == null ? null : sayTitle.trim();
    }

    public String getSayContext() {
        return sayContext;
    }

    public void setSayContext(String sayContext) {
        this.sayContext = sayContext == null ? null : sayContext.trim();
    }

    public String getSayLogo() {
        return sayLogo;
    }

    public void setSayLogo(String sayLogo) {
        this.sayLogo = sayLogo == null ? null : sayLogo.trim();
    }

    public String getSayHeadImg() {
        return sayHeadImg;
    }

    public void setSayHeadImg(String sayHeadImg) {
        this.sayHeadImg = sayHeadImg == null ? null : sayHeadImg.trim();
    }

    public String getSayName() {
        return sayName;
    }

    public void setSayName(String sayName) {
        this.sayName = sayName == null ? null : sayName.trim();
    }

    public Integer getSayEvalNum() {
        return sayEvalNum;
    }

    public void setSayEvalNum(Integer sayEvalNum) {
        this.sayEvalNum = sayEvalNum;
    }

    public Integer getSayThumbsNum() {
        return sayThumbsNum;
    }

    public void setSayThumbsNum(Integer sayThumbsNum) {
        this.sayThumbsNum = sayThumbsNum;
    }

    public Integer getCreateBy() {
        return createBy;
    }

    public void setCreateBy(Integer createBy) {
        this.createBy = createBy;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Integer getUpdateBy() {
        return updateBy;
    }

    public void setUpdateBy(Integer updateBy) {
        this.updateBy = updateBy;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    public String getSayStatus() {
        return sayStatus;
    }

    public void setSayStatus(String sayStatus) {
        this.sayStatus = sayStatus == null ? null : sayStatus.trim();
    }

}