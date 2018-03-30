package com.huisou.vo;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import com.huisou.po.ResRebateSetPo;

/** 
* @author 作者 :yuhao 
* @version 创建时间：2018年1月30日 上午9:33:09 
* 类说明 
*/
public class VideoAudioVo implements Serializable{
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
    /**
     * 课程名称
     */
    private String courseTitle;
    
    private String videoAudioIscourse;

    private String videoAudioIspay;

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
    
    /**
     * 该资源的所有评论的平均等级
     */
    private Integer avgEvalLevel;
    /**
     * 是否播放
     */
    private String IsPlay;
    
    
   private List<ResRebateSetPo> resRebateSetList;
    
    
    
    public List<ResRebateSetPo> getResRebateSetList() {
	return resRebateSetList;
}

public void setResRebateSetList(List<ResRebateSetPo> resRebateSetList) {
	this.resRebateSetList = resRebateSetList;
}

	public String getIsPlay() {
		return IsPlay;
	}

	public void setIsPlay(String isPlay) {
		IsPlay = isPlay;
	}

	public Integer getAvgEvalLevel() {
		return avgEvalLevel;
	}

	public void setAvgEvalLevel(Integer avgEvalLevel) {
		this.avgEvalLevel = avgEvalLevel;
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
    
    public String getCourseTitle() {
        return courseTitle;
    }

    public void setCourseTitle(String courseTitle) {
        this.courseTitle = courseTitle == null ? null : courseTitle.trim();
    }
}
