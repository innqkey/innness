package com.huisou.po;

import java.util.Date;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
@Table(name = "busi_course_witness")
public class CourseWitnessPo {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer witnessId;

    private String witnessContext;

    private String witnessLogo;

    private Integer courseId;

    private Integer createBy;

    private Date createTime;

    private Integer updateBy;

    private Date updateTime;

    private String witnessStatus;

    private String standby1;

    private String standby2;

    public Integer getWitnessId() {
        return witnessId;
    }

    public void setWitnessId(Integer witnessId) {
        this.witnessId = witnessId;
    }

    public String getWitnessContext() {
        return witnessContext;
    }

    public void setWitnessContext(String witnessContext) {
        this.witnessContext = witnessContext == null ? null : witnessContext.trim();
    }

    public String getWitnessLogo() {
        return witnessLogo;
    }

    public void setWitnessLogo(String witnessLogo) {
        this.witnessLogo = witnessLogo == null ? null : witnessLogo.trim();
    }

    public Integer getCourseId() {
        return courseId;
    }

    public void setCourseId(Integer courseId) {
        this.courseId = courseId;
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

    public String getWitnessStatus() {
        return witnessStatus;
    }

    public void setWitnessStatus(String witnessStatus) {
        this.witnessStatus = witnessStatus == null ? null : witnessStatus.trim();
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