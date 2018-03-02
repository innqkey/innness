package com.huisou.vo;

import java.util.List;

import com.huisou.po.RegionPo;

/** 
* @author 作者 :yuhao 
* @version 创建时间：2018年2月26日 下午3:31:56 
* 类说明 
*/
public class ChildRegionVo {
	
	private Integer id;
	private Integer parent;
	private String path;
	private Integer grade;
	private String name;
	private String first_word;
	private String enabled;
	
	private List<RegionPo> child;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getParent() {
		return parent;
	}

	public void setParent(Integer parent) {
		this.parent = parent;
	}

	public String getPath() {
		return path;
	}

	public void setPath(String path) {
		this.path = path;
	}

	public Integer getGrade() {
		return grade;
	}

	public void setGrade(Integer grade) {
		this.grade = grade;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getFirst_word() {
		return first_word;
	}

	public void setFirst_word(String first_word) {
		this.first_word = first_word;
	}

	public String getEnabled() {
		return enabled;
	}

	public void setEnabled(String enabled) {
		this.enabled = enabled;
	}

	public List<RegionPo> getChild() {
		return child;
	}

	public void setChild(List<RegionPo> child) {
		this.child = child;
	}
	
	
	
}
