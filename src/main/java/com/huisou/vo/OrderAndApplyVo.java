package com.huisou.vo;

import java.util.Date;
import java.util.List;

import com.huisou.po.RegistPo;

/** 
* @author 作者 :yuhao 
* @version 创建时间：2018年2月7日 下午5:14:24 
* 类说明 
*/
public class OrderAndApplyVo {
	
	 private Integer orderId;

	 private String orderNo;

	    private Integer recentCourseId;

	    private Integer userId;
	    private String recentCourseTitle;

	    private String recentCourseAddress;

	    private String recentCourseMaxNum;

	    private String recentCourseApplyNum;

	    private Integer courseId;

	    private Date beginTime;

	    private Date endTime;
	    
	    
	    private String recentCourseDeleteStatus;
	    
	    private String recentCourseStatus;
	    /**
	     * 报名时间
	     */
	    private Date createTime;

	    private List<RegistPo> list;

		public Integer getOrderId() {
			return orderId;
		}

		public void setOrderId(Integer orderId) {
			this.orderId = orderId;
		}

		public String getOrderNo() {
			return orderNo;
		}

		public void setOrderNo(String orderNo) {
			this.orderNo = orderNo;
		}

		public Integer getRecentCourseId() {
			return recentCourseId;
		}

		public void setRecentCourseId(Integer recentCourseId) {
			this.recentCourseId = recentCourseId;
		}

		public Integer getUserId() {
			return userId;
		}

		public void setUserId(Integer userId) {
			this.userId = userId;
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

		public String getRecentCourseDeleteStatus() {
			return recentCourseDeleteStatus;
		}

		public void setRecentCourseDeleteStatus(String recentCourseDeleteStatus) {
			this.recentCourseDeleteStatus = recentCourseDeleteStatus;
		}

		public String getRecentCourseStatus() {
			return recentCourseStatus;
		}

		public void setRecentCourseStatus(String recentCourseStatus) {
			this.recentCourseStatus = recentCourseStatus;
		}

		public Date getCreateTime() {
			return createTime;
		}

		public void setCreateTime(Date createTime) {
			this.createTime = createTime;
		}

		public List<RegistPo> getList() {
			return list;
		}

		public void setList(List<RegistPo> list) {
			this.list = list;
		}
	    
}
