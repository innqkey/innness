package com.huisou.po;

import java.util.Date;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
@Table(name = "busi_recent_course")
public class RecentCoursePo {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer recentCourseId;

    private String recentCourseTitle;

    private String recentCourseAddress;

    private String recentCourseMaxNum;

    private String recentCourseApplyNum;

    private String regionsids;

    private String province;

    private String city;

    private String area;

    private Integer courseId;

    private Date beginTime;

    private Date endTime;

    private Integer createBy;

    private Date createTime;

    private Integer updateBy;

    private Date updateTime;

    private String recentCourseDeleteStatus;

    private String recentCourseStatus;

    private String standby1;

    private String standby2;

    public Integer getRecentCourseId() {
        return recentCourseId;
    }

    public void setRecentCourseId(Integer recentCourseId) {
        this.recentCourseId = recentCourseId;
    }

    public String getRecentCourseTitle() {
        return recentCourseTitle;
    }

    public void setRecentCourseTitle(String recentCourseTitle) {
        this.recentCourseTitle = recentCourseTitle == null ? null : recentCourseTitle.trim();
    }

    public String getRecentCourseAddress() {
        return recentCourseAddress;
    }

    public void setRecentCourseAddress(String recentCourseAddress) {
        this.recentCourseAddress = recentCourseAddress == null ? null : recentCourseAddress.trim();
    }

    public String getRecentCourseMaxNum() {
        return recentCourseMaxNum;
    }

    public void setRecentCourseMaxNum(String recentCourseMaxNum) {
        this.recentCourseMaxNum = recentCourseMaxNum == null ? null : recentCourseMaxNum.trim();
    }

    public String getRecentCourseApplyNum() {
        return recentCourseApplyNum;
    }

    public void setRecentCourseApplyNum(String recentCourseApplyNum) {
        this.recentCourseApplyNum = recentCourseApplyNum == null ? null : recentCourseApplyNum.trim();
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

    public Integer getCourseId() {
        return courseId;
    }

    public void setCourseId(Integer courseId) {
        this.courseId = courseId;
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

    public String getRecentCourseDeleteStatus() {
        return recentCourseDeleteStatus;
    }

    public void setRecentCourseDeleteStatus(String recentCourseDeleteStatus) {
        this.recentCourseDeleteStatus = recentCourseDeleteStatus == null ? null : recentCourseDeleteStatus.trim();
    }

    public String getRecentCourseStatus() {
        return recentCourseStatus;
    }

    public void setRecentCourseStatus(String recentCourseStatus) {
        this.recentCourseStatus = recentCourseStatus == null ? null : recentCourseStatus.trim();
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