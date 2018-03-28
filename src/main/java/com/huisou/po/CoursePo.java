package com.huisou.po;

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

    private Long coursePrice;

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

    private Long oldMoney;

    private String courseAddress;

    private String courseMaxNum;

    private String courseApplyNum;

    private String regionsids;

    private String province;

    private String city;

    private String area;

    private Date beginTime;

    private Date endTime;

    private String isStartStatus;

    private Integer underApplyNum;

    private String standby4;

    private String standby3;

    private String courseIntro;

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

    public Long getCoursePrice() {
        return coursePrice;
    }

    public void setCoursePrice(Long coursePrice) {
        this.coursePrice = coursePrice;
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

    public Long getOldMoney() {
        return oldMoney;
    }

    public void setOldMoney(Long oldMoney) {
        this.oldMoney = oldMoney;
    }

    public String getCourseAddress() {
        return courseAddress;
    }

    public void setCourseAddress(String courseAddress) {
        this.courseAddress = courseAddress == null ? null : courseAddress.trim();
    }

    public String getCourseMaxNum() {
        return courseMaxNum;
    }

    public void setCourseMaxNum(String courseMaxNum) {
        this.courseMaxNum = courseMaxNum == null ? null : courseMaxNum.trim();
    }

    public String getCourseApplyNum() {
        return courseApplyNum;
    }

    public void setCourseApplyNum(String courseApplyNum) {
        this.courseApplyNum = courseApplyNum == null ? null : courseApplyNum.trim();
    }

    public String getRegionsids() {
        return regionsids;
    }

    public void setRegionsids(String regionsids) {
        this.regionsids = regionsids == null ? null : regionsids.trim();
    }

    public String getProvince() {
        return province;
    }

    public void setProvince(String province) {
        this.province = province == null ? null : province.trim();
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city == null ? null : city.trim();
    }

    public String getArea() {
        return area;
    }

    public void setArea(String area) {
        this.area = area == null ? null : area.trim();
    }

    public Date getBeginTime() {
        return beginTime;
    }

    public void setBeginTime(Date beginTime) {
        this.beginTime = beginTime;
    }

    public Date getEndTime() {
        return endTime;
    }

    public void setEndTime(Date endTime) {
        this.endTime = endTime;
    }

    public String getIsStartStatus() {
        return isStartStatus;
    }

    public void setIsStartStatus(String isStartStatus) {
        this.isStartStatus = isStartStatus == null ? null : isStartStatus.trim();
    }

    public Integer getUnderApplyNum() {
        return underApplyNum;
    }

    public void setUnderApplyNum(Integer underApplyNum) {
        this.underApplyNum = underApplyNum;
    }

    public String getStandby4() {
        return standby4;
    }

    public void setStandby4(String standby4) {
        this.standby4 = standby4 == null ? null : standby4.trim();
    }

    public String getStandby3() {
        return standby3;
    }

    public void setStandby3(String standby3) {
        this.standby3 = standby3 == null ? null : standby3.trim();
    }

    public String getCourseIntro() {
        return courseIntro;
    }

    public void setCourseIntro(String courseIntro) {
        this.courseIntro = courseIntro == null ? null : courseIntro.trim();
    }

    public String getCourseDetail() {
        return courseDetail;
    }

    public void setCourseDetail(String courseDetail) {
        this.courseDetail = courseDetail == null ? null : courseDetail.trim();
    }
}