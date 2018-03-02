package com.huisou.po;

import java.util.Date;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
@Table(name = "busi_material_level")
public class MaterialLevelPo {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer materialLevelId;

    private String materialLevelName;

    private String materialLevelType;

    private String materialLevelImg;

    private Integer materialLevelNum;
    private String materialLevelStatus;
    
    private Integer createBy;

    private Date createTime;

    private String standby1;

    private String standby2;

    public Integer getMaterialLevelId() {
        return materialLevelId;
    }

    public void setMaterialLevelId(Integer materialLevelId) {
        this.materialLevelId = materialLevelId;
    }

    public String getMaterialLevelName() {
        return materialLevelName;
    }

    public void setMaterialLevelName(String materialLevelName) {
        this.materialLevelName = materialLevelName == null ? null : materialLevelName.trim();
    }

    public String getMaterialLevelType() {
        return materialLevelType;
    }

    public void setMaterialLevelType(String materialLevelType) {
        this.materialLevelType = materialLevelType == null ? null : materialLevelType.trim();
    }

    public String getMaterialLevelImg() {
        return materialLevelImg;
    }

    public void setMaterialLevelImg(String materialLevelImg) {
        this.materialLevelImg = materialLevelImg == null ? null : materialLevelImg.trim();
    }

    public Integer getMaterialLevelNum() {
        return materialLevelNum;
    }

    public void setMaterialLevelNum(Integer materialLevelNum) {
        this.materialLevelNum = materialLevelNum;
    }

    
    public String getMaterialLevelStatus() {
		return materialLevelStatus;
	}

	public void setMaterialLevelStatus(String materialLevelStatus) {
		this.materialLevelStatus = materialLevelStatus;
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

    public String getStandby1() {
        return standby1;
    }

    public void setStandby1(String standby1) {
        this.standby1 = standby1 == null ? null : standby1.trim();
    }

    public String getStandby2() {
        return standby2;
    }

    public void setStandby2(String standby2) {
        this.standby2 = standby2 == null ? null : standby2.trim();
    }
}