package com.huisou.vo;
/**
* 类说明：
* @author 
* @version 创建时间：2018年3月23日 上午11:11:08
* 
*/

import java.io.Serializable;
import java.util.Date;

public class MemberWithdrawVo implements Serializable{
	 /**
	 * 
	 */
	private static final long serialVersionUID = -4554159783162960872L;
	private Integer userid;
	 private String username;
	 private String nickname;
	 private String phone;
	 private String  membersetname;
	 private Date createtime;
	 private Long withdrawaccount;
	 private String recorestatus;
	 private Integer withdrawalrecordid;
	 private String userbankaccount;
	 private String userbankname;
	 private String userbanktype;
	 
	 
	public String getUserbankaccount() {
		return userbankaccount;
	}
	public void setUserbankaccount(String userbankaccount) {
		this.userbankaccount = userbankaccount;
	}
	public String getUserbankname() {
		return userbankname;
	}
	public void setUserbankname(String userbankname) {
		this.userbankname = userbankname;
	}
	public String getUserbanktype() {
		return userbanktype;
	}
	public void setUserbanktype(String userbanktype) {
		this.userbanktype = userbanktype;
	}
	public Integer getUserid() {
		return userid;
	}
	public void setUserid(Integer userid) {
		this.userid = userid;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getNickname() {
		return nickname;
	}
	public void setNickname(String nickname) {
		this.nickname = nickname;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public String getMembersetname() {
		return membersetname;
	}
	public void setMembersetname(String membersetname) {
		this.membersetname = membersetname;
	}
	public Date getCreatetime() {
		return createtime;
	}
	public void setCreatetime(Date createtime) {
		this.createtime = createtime;
	}
	public Long getWithdrawaccount() {
		return withdrawaccount;
	}
	public void setWithdrawaccount(Long withdrawaccount) {
		this.withdrawaccount = withdrawaccount;
	}
	public String getRecorestatus() {
		return recorestatus;
	}
	public void setRecorestatus(String recorestatus) {
		this.recorestatus = recorestatus;
	}
	public Integer getWithdrawalrecordid() {
		return withdrawalrecordid;
	}
	public void setWithdrawalrecordid(Integer withdrawalrecordid) {
		this.withdrawalrecordid = withdrawalrecordid;
	}
	
}
