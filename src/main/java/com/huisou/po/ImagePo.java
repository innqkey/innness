package com.huisou.po;

import java.util.Date;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
@Table(name = "busi_image")
public class ImagePo {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer imageId;

    private String userOpenid;

    private Integer backgroudId;

    private Integer status;

    private Integer imageType;

    private String qcodeUrl;

    private String fileName;

    private Date createTime;

    private String standby1;

    private String standby2;

    public Integer getImageId() {
        return imageId;
    }

    public void setImageId(Integer imageId) {
        this.imageId = imageId;
    }

    public String getUserOpenid() {
        return userOpenid;
    }

    public void setUserOpenid(String userOpenid) {
        this.userOpenid = userOpenid == null ? null : userOpenid.trim();
    }

    public Integer getBackgroudId() {
        return backgroudId;
    }

    public void setBackgroudId(Integer backgroudId) {
        this.backgroudId = backgroudId;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Integer getImageType() {
        return imageType;
    }

    public void setImageType(Integer imageType) {
        this.imageType = imageType;
    }

    public String getQcodeUrl() {
        return qcodeUrl;
    }

    public void setQcodeUrl(String qcodeUrl) {
        this.qcodeUrl = qcodeUrl == null ? null : qcodeUrl.trim();
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName == null ? null : fileName.trim();
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