package com.huisou.vo;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

/** 
* @author 作者 :yuhao 
* @version 创建时间：2018年2月9日 下午3:41:52 
* 类说明 
*/
public class CourseVo implements Serializable{
	  private Integer courseId;

	    private String courseTitle;

	    private BigDecimal coursePrice;
	    private BigDecimal oldMoney;

	    private String courseIntro;

	    private String courseSpeaker;

	    private String courseDuration;
	    /**
	     * 课程图片
	     */
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
	    
	    private String videoaudioIds;
	    
	    private String introvideoUrl;
	    
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
	    
	    private String ispay;
	    
	    private Integer remainNum;
	    

	    private Integer underApplyNum;

	    
		public Integer getUnderApplyNum() {
			return underApplyNum;
		}

		public void setUnderApplyNum(Integer underApplyNum) {
			this.underApplyNum = underApplyNum;
		}

		public Integer getRemainNum() {
			return remainNum;
		}

		public void setRemainNum(Integer remainNum) {
			this.remainNum = remainNum;
		}

		public String getIspay() {
			return ispay;
		}

		public void setIspay(String ispay) {
			this.ispay = ispay;
		}

		public String getCourseAddress() {
			return courseAddress;
		}

		public void setCourseAddress(String courseAddress) {
			this.courseAddress = courseAddress;
		}

		public String getCourseMaxNum() {
			return courseMaxNum;
		}

		public void setCourseMaxNum(String courseMaxNum) {
			this.courseMaxNum = courseMaxNum;
		}

		public String getCourseApplyNum() {
			return courseApplyNum;
		}

		public void setCourseApplyNum(String courseApplyNum) {
			this.courseApplyNum = courseApplyNum;
		}

		public String getRegionsids() {
			return regionsids;
		}

		public void setRegionsids(String regionsids) {
			this.regionsids = regionsids;
		}

		public String getProvince() {
			return province;
		}

		public void setProvince(String province) {
			this.province = province;
		}

		public String getCity() {
			return city;
		}

		public void setCity(String city) {
			this.city = city;
		}

		public String getArea() {
			return area;
		}

		public void setArea(String area) {
			this.area = area;
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
			this.isStartStatus = isStartStatus;
		}

		public String getIntrovideoUrl() {
			return introvideoUrl;
		}

		public void setIntrovideoUrl(String introvideoUrl) {
			this.introvideoUrl = introvideoUrl;
		}

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
			this.courseTitle = courseTitle;
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
			this.courseIntro = courseIntro;
		}

		public String getCourseSpeaker() {
			return courseSpeaker;
		}

		public void setCourseSpeaker(String courseSpeaker) {
			this.courseSpeaker = courseSpeaker;
		}

		public String getCourseDuration() {
			return courseDuration;
		}

		public void setCourseDuration(String courseDuration) {
			this.courseDuration = courseDuration;
		}

		public String getCoursePicture() {
			return coursePicture;
		}

		public void setCoursePicture(String coursePicture) {
			this.coursePicture = coursePicture;
		}

		public String getCourseLogo() {
			return courseLogo;
		}

		public void setCourseLogo(String courseLogo) {
			this.courseLogo = courseLogo;
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
			this.courseStatus = courseStatus;
		}

		public String getStandby1() {
			return standby1;
		}

		public void setStandby1(String standby1) {
			this.standby1 = standby1;
		}

		public String getStandby2() {
			return standby2;
		}

		public void setStandby2(String standby2) {
			this.standby2 = standby2;
		}

		public String getCourseDetail() {
			return courseDetail;
		}

		public void setCourseDetail(String courseDetail) {
			this.courseDetail = courseDetail;
		}

		public String getVideoaudioIds() {
			return videoaudioIds;
		}

		public void setVideoaudioIds(String videoaudioIds) {
			this.videoaudioIds = videoaudioIds;
		}

		public BigDecimal getOldMoney() {
			return oldMoney;
		}

		public void setOldMoney(BigDecimal oldMoney) {
			this.oldMoney = oldMoney;
		}
	    
	    
}
