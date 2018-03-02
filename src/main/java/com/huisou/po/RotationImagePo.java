package com.huisou.po;

import java.util.Date;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
@Table(name = "busi_rotation_image")
public class RotationImagePo {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer rotationImageId;

    private Integer rotationImageNo;

    private Integer noticeCertificateId;

    private String noticeCertificateContent;

    private String rotationImageName;

    private String rotationImageUrl;

    private String linkUrlType;

    private String linkUrl;

    private Integer createBy;

    private Date createTime;

    private Integer updateBy;

    private Date updateTime;

    private String rotationImageStatus;

    private String standby1;

    private String standby2;

    public Integer getRotationImageId() {
        return rotationImageId;
    }

    public void setRotationImageId(Integer rotationImageId) {
        this.rotationImageId = rotationImageId;
    }

    public Integer getRotationImageNo() {
        return rotationImageNo;
    }

    public void setRotationImageNo(Integer rotationImageNo) {
        this.rotationImageNo = rotationImageNo;
    }

    public Integer getNoticeCertificateId() {
        return noticeCertificateId;
    }

    public void setNoticeCertificateId(Integer noticeCertificateId) {
        this.noticeCertificateId = noticeCertificateId;
    }

    public String getNoticeCertificateContent() {
        return noticeCertificateContent;
    }

    public void setNoticeCertificateContent(String noticeCertificateContent) {
        this.noticeCertificateContent = noticeCertificateContent == null ? null : noticeCertificateContent.trim();
    }

    public String getRotationImageName() {
        return rotationImageName;
    }

    public void setRotationImageName(String rotationImageName) {
        this.rotationImageName = rotationImageName == null ? null : rotationImageName.trim();
    }

    public String getRotationImageUrl() {
        return rotationImageUrl;
    }

    public void setRotationImageUrl(String rotationImageUrl) {
        this.rotationImageUrl = rotationImageUrl == null ? null : rotationImageUrl.trim();
    }

    public String getLinkUrlType() {
        return linkUrlType;
    }

    public void setLinkUrlType(String linkUrlType) {
        this.linkUrlType = linkUrlType == null ? null : linkUrlType.trim();
    }

    public String getLinkUrl() {
        return linkUrl;
    }

    public void setLinkUrl(String linkUrl) {
        this.linkUrl = linkUrl == null ? null : linkUrl.trim();
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

    public String getRotationImageStatus() {
        return rotationImageStatus;
    }

    public void setRotationImageStatus(String rotationImageStatus) {
        this.rotationImageStatus = rotationImageStatus == null ? null : rotationImageStatus.trim();
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