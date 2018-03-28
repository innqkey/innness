package com.huisou.po;

import java.math.BigDecimal;
import java.util.Date;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
@Table(name = "busi_video_audio")
public class VideoAudioPo {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer videoAudioId;

    private String videoAudioTitle;

    private BigDecimal videoAudioPrice;

    private String videoAudioIntro;

    private String videoAudioLogo;
    
    /**
     * 视频音频的路径
     */
    private String videoAudioUrl;
    /**
     * 视音类型：SP-视频；YP-语音
     */
    private String videoAudioType;

    private Integer videoAudioNo;

    private Integer courseId;

    private String videoAudioIscourse;

    private String videoAudioIspay;
    
    /**
     * 是否置顶
     */
    private String videoAudioIstop;

    private String videoAudioIsonline;

    private Integer videoAudioPayNum;

    private Integer createBy;

    private Date createTime;

    private Integer updateBy;

    private Date updateTime;

    private String videoAudioStatus;

    private String standby1;

    private String standby2;

    private Integer realVideoAudioPayNum;
    
    public Integer getRealVideoAudioPayNum() {
		return realVideoAudioPayNum;
	}

	public void setRealVideoAudioPayNum(Integer realVideoAudioPayNum) {
		this.realVideoAudioPayNum = realVideoAudioPayNum;
	}

	public String getVideoAudioUrl() {
		return videoAudioUrl;
	}

	public void setVideoAudioUrl(String videoAudioUrl) {
		this.videoAudioUrl = videoAudioUrl;
	}

	public Integer getVideoAudioId() {
        return videoAudioId;
    }

    public void setVideoAudioId(Integer videoAudioId) {
        this.videoAudioId = videoAudioId;
    }

    public String getVideoAudioTitle() {
        return videoAudioTitle;
    }

    public void setVideoAudioTitle(String videoAudioTitle) {
        this.videoAudioTitle = videoAudioTitle == null ? null : videoAudioTitle.trim();
    }

    public BigDecimal getVideoAudioPrice() {
        return videoAudioPrice;
    }

    public void setVideoAudioPrice(BigDecimal videoAudioPrice) {
        this.videoAudioPrice = videoAudioPrice;
    }

    public String getVideoAudioIntro() {
        return videoAudioIntro;
    }

    public void setVideoAudioIntro(String videoAudioIntro) {
        this.videoAudioIntro = videoAudioIntro == null ? null : videoAudioIntro.trim();
    }

    public String getVideoAudioLogo() {
        return videoAudioLogo;
    }

    public void setVideoAudioLogo(String videoAudioLogo) {
        this.videoAudioLogo = videoAudioLogo == null ? null : videoAudioLogo.trim();
    }

    public String getVideoAudioType() {
        return videoAudioType;
    }

    public void setVideoAudioType(String videoAudioType) {
        this.videoAudioType = videoAudioType == null ? null : videoAudioType.trim();
    }

    public Integer getVideoAudioNo() {
        return videoAudioNo;
    }

    public void setVideoAudioNo(Integer videoAudioNo) {
        this.videoAudioNo = videoAudioNo;
    }

    public Integer getCourseId() {
        return courseId;
    }

    public void setCourseId(Integer courseId) {
        this.courseId = courseId;
    }

    public String getVideoAudioIscourse() {
        return videoAudioIscourse;
    }

    public void setVideoAudioIscourse(String videoAudioIscourse) {
        this.videoAudioIscourse = videoAudioIscourse == null ? null : videoAudioIscourse.trim();
    }

    public String getVideoAudioIspay() {
        return videoAudioIspay;
    }

    public void setVideoAudioIspay(String videoAudioIspay) {
        this.videoAudioIspay = videoAudioIspay == null ? null : videoAudioIspay.trim();
    }

    public String getVideoAudioIstop() {
        return videoAudioIstop;
    }

    public void setVideoAudioIstop(String videoAudioIstop) {
        this.videoAudioIstop = videoAudioIstop == null ? null : videoAudioIstop.trim();
    }

    public String getVideoAudioIsonline() {
        return videoAudioIsonline;
    }

    public void setVideoAudioIsonline(String videoAudioIsonline) {
        this.videoAudioIsonline = videoAudioIsonline == null ? null : videoAudioIsonline.trim();
    }

    public Integer getVideoAudioPayNum() {
        return videoAudioPayNum;
    }

    public void setVideoAudioPayNum(Integer videoAudioPayNum) {
        this.videoAudioPayNum = videoAudioPayNum;
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

    public String getVideoAudioStatus() {
        return videoAudioStatus;
    }

    public void setVideoAudioStatus(String videoAudioStatus) {
        this.videoAudioStatus = videoAudioStatus == null ? null : videoAudioStatus.trim();
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