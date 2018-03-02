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

    private Integer imageType;

    private String fileName;

    private Date createTime;

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

    public Integer getImageType() {
        return imageType;
    }

    public void setImageType(Integer imageType) {
        this.imageType = imageType;
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
}