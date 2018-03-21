package com.huisou.vo;

import java.io.Serializable;
import java.util.Date;

/** 
* @author 作者 :yuhao 
* @version 创建时间：2018年1月30日 下午3:40:03 
* 类说明 
*/
public class RecentCourseVo implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 4360056556079201079L;
	 private Integer recentCourseId;

	    private String recentCourseTitle;

	    private String recentCourseAddress;

	    private String recentCourseMaxNum;

	    private String recentCourseApplyNum;

	    private Integer courseId;

	    private Date beginTime;

	    private Date endTime;
	    
	    private Date createTime;
	    
	    private String recentCourseDeleteStatus;
	    
	    private String recentCourseStatus;
	    
	    private String courseTitle;
	    
	    private String isApply;
	    
	    private Integer remainNum;
	    
	    private String regionsids;
	    
	    
	    
	    
		public String getRegionsids() {
			return regionsids;
		}

		public void setRegionsids(String regionsids) {
			this.regionsids = regionsids;
		}

		public Integer getRemainNum() {
			return remainNum;
		}

		public void setRemainNum(Integer remainNum) {
			this.remainNum = remainNum;
		}

		public String getIsApply() {
			return isApply;
		}

		public void setIsApply(String isApply) {
			this.isApply = isApply;
		}

		public String getRecentCourseDeleteStatus() {
			return recentCourseDeleteStatus;
		}

		public void setRecentCourseDeleteStatus(String recentCourseDeleteStatus) {
			this.recentCourseDeleteStatus = recentCourseDeleteStatus;
		}

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
			this.recentCourseTitle = recentCourseTitle;
		}

		public String getRecentCourseAddress() {
			return recentCourseAddress;
		}

		public void setRecentCourseAddress(String recentCourseAddress) {
			this.recentCourseAddress = recentCourseAddress;
		}

		public String getRecentCourseMaxNum() {
			return recentCourseMaxNum;
		}

		public void setRecentCourseMaxNum(String recentCourseMaxNum) {
			this.recentCourseMaxNum = recentCourseMaxNum;
		}

		public String getRecentCourseApplyNum() {
			return recentCourseApplyNum;
		}

		public void setRecentCourseApplyNum(String recentCourseApplyNum) {
			this.recentCourseApplyNum = recentCourseApplyNum;
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

		public Date getCreateTime() {
			return createTime;
		}

		public void setCreateTime(Date createTime) {
			this.createTime = createTime;
		}

		public String getRecentCourseStatus() {
			return recentCourseStatus;
		}

		public void setRecentCourseStatus(String recentCourseStatus) {
			this.recentCourseStatus = recentCourseStatus;
		}

		public String getCourseTitle() {
			return courseTitle;
		}

		public void setCourseTitle(String courseTitle) {
			this.courseTitle = courseTitle;
		}
	    
	    

}
