package com.huisou.po;

import java.util.Date;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Table(name = "busi_material")
public class MaterialPo {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer materialId;

    private String materialName;

    private String materialIntro;

    private String materialUrl;

    private Integer materialLevelId;

    private Long materialIntegral;

    private String materialIspay;

    private Integer materialDownNum;

    private String materialUrlStatus;

    private Integer createBy;

    private Date createTime;

    private String standby1;

    private String standby2;

    public Integer getMaterialId() {
        return materialId;
    }

    public void setMaterialId(Integer materialId) {
        this.materialId = materialId;
    }

    public String getMaterialName() {
        return materialName;
    }

    public void setMaterialName(String materialName) {
        this.materialName = materialName == null ? null : materialName.trim();
    }

    public String getMaterialIntro() {
        return materialIntro;
    }

    public void setMaterialIntro(String materialIntro) {
        this.materialIntro = materialIntro == null ? null : materialIntro.trim();
    }

    public String getMaterialUrl() {
        return materialUrl;
    }

    public void setMaterialUrl(String materialUrl) {
        this.materialUrl = materialUrl == null ? null : materialUrl.trim();
    }

    public Integer getMaterialLevelId() {
        return materialLevelId;
    }

    public void setMaterialLevelId(Integer materialLevelId) {
        this.materialLevelId = materialLevelId;
    }

    public Long getMaterialIntegral() {
        return materialIntegral;
    }

    public void setMaterialIntegral(Long materialIntegral) {
        this.materialIntegral = materialIntegral;
    }

    public String getMaterialIspay() {
        return materialIspay;
    }

    public void setMaterialIspay(String materialIspay) {
        this.materialIspay = materialIspay == null ? null : materialIspay.trim();
    }

    public Integer getMaterialDownNum() {
        return materialDownNum;
    }

    public void setMaterialDownNum(Integer materialDownNum) {
        this.materialDownNum = materialDownNum;
    }

    public String getMaterialUrlStatus() {
        return materialUrlStatus;
    }

    public void setMaterialUrlStatus(String materialUrlStatus) {
        this.materialUrlStatus = materialUrlStatus;
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