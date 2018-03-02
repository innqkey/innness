package com.huisou.po;

import java.math.BigDecimal;
import java.util.Date;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
@Table(name = "busi_course")
public class CoursePo {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer courseId;

    private String courseTitle;

    private BigDecimal coursePrice;

    private String courseIntro;

    private String courseSpeaker;

    private String courseDuration;

    private String introvideoUrl;

    private String coursePicture;

    private String courseLogo;

    private Integer createBy;

    private Date createTime;

    private Integer updateBy;

    private Date updateTime;

    private String courseStatus;

    private String standby1;

    private String standby2;

    private String courseDetail;

    public Integer getCourseId() {
        return courseId;
    }

    public void setCourseId(Integer courseId) {
        this.courseId = courseId;
    }

    public String getCourseTitle() {
        return courseTitle;
    }

    public void setCourseTitle(String courseTitle) {
        this.courseTitle = courseTitle == null ? null : courseTitle.trim();
    }

    public BigDecimal getCoursePrice() {
        return coursePrice;
    }

    public void setCoursePrice(BigDecimal coursePrice) {
        this.coursePrice = coursePrice;
    }

    public String getCourseIntro() {
        return courseIntro;
    }

    public void setCourseIntro(String courseIntro) {
        this.courseIntro = courseIntro == null ? null : courseIntro.trim();
    }

    public String getCourseSpeaker() {
        return courseSpeaker;
    }

    public void setCourseSpeaker(String courseSpeaker) {
        this.courseSpeaker = courseSpeaker == null ? null : courseSpeaker.trim();
    }

    public String getCourseDuration() {
        return courseDuration;
    }

    public void setCourseDuration(String courseDuration) {
        this.courseDuration = courseDuration == null ? null : courseDuration.trim();
    }

    public String getIntrovideoUrl() {
        return introvideoUrl;
    }

    public void setIntrovideoUrl(String introvideoUrl) {
        this.introvideoUrl = introvideoUrl == null ? null : introvideoUrl.trim();
    }

    public String getCoursePicture() {
        return coursePicture;
    }

    public void setCoursePicture(String coursePicture) {
        this.coursePicture = coursePicture == null ? null : coursePicture.trim();
    }

    public String getCourseLogo() {
        return courseLogo;
    }

    public void setCourseLogo(String courseLogo) {
        this.courseLogo = courseLogo == null ? null : courseLogo.trim();
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

    public String getCourseStatus() {
        return courseStatus;
    }

    public void setCourseStatus(String courseStatus) {
        this.courseStatus = courseStatus == null ? null : courseStatus.trim();
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

    public String getCourseDetail() {
        return courseDetail;
    }

    public void setCourseDetail(String courseDetail) {
        this.courseDetail = courseDetail == null ? null : courseDetail.trim();
    }
}