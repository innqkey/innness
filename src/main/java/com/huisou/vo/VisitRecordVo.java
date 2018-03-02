package com.huisou.vo;

import java.io.Serializable;

public class VisitRecordVo implements Serializable{
	private static final long serialVersionUID = 1L;
	private Integer visitId;
	private Integer lastDayDlCount;
    private Integer lastDayYpCount;
    private Integer lastDaySpCount;
    private Integer lastDayZlCount;
    private Integer lastDayOrderCount;
    private Integer count;
    private String visitType;
    
	public Integer getLastDayDlCount() {
		return lastDayDlCount;
	}
	public void setLastDayDlCount(Integer lastDayDlCount) {
		this.lastDayDlCount = lastDayDlCount;
	}
	public Integer getVisitId() {
		return visitId;
	}
	public void setVisitId(Integer visitId) {
		this.visitId = visitId;
	}
	public Integer getLastDayYpCount() {
		return lastDayYpCount;
	}
	public void setLastDayYpCount(Integer lastDayYpCount) {
		this.lastDayYpCount = lastDayYpCount;
	}
	public Integer getLastDaySpCount() {
		return lastDaySpCount;
	}
	public void setLastDaySpCount(Integer lastDaySpCount) {
		this.lastDaySpCount = lastDaySpCount;
	}
	public Integer getLastDayZlCount() {
		return lastDayZlCount;
	}
	public void setLastDayZlCount(Integer lastDayZlCount) {
		this.lastDayZlCount = lastDayZlCount;
	}
	public Integer getLastDayOrderCount() {
		return lastDayOrderCount;
	}
	public void setLastDayOrderCount(Integer lastDayOrderCount) {
		this.lastDayOrderCount = lastDayOrderCount;
	}
	public Integer getCount() {
		return count;
	}
	public void setCount(Integer count) {
		this.count = count;
	}
	public String getVisitType() {
		return visitType;
	}
	public void setVisitType(String visitType) {
		this.visitType = visitType;
	}
    
    
}