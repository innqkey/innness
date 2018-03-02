package com.huisou.vo;

import java.io.Serializable;

/** 
* @author 作者 :yuhao 
* @version 创建时间：2018年2月28日 下午1:58:05 
* 类说明 
*/
public class RegistVo implements Serializable{
	private String data_type;
	
	private String name;

	public String getData_type() {
		return data_type;
	}

	public void setData_type(String data_type) {
		this.data_type = data_type;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	
}
