package com.huisou.vo;

import java.util.Date;

/** 
* @author 作者 :yuhao 
* @version 创建时间：2018年1月30日 下午1:47:49 
* 类说明 
*/
public class AwardRecordVo {
	
	 private Integer awardRecordId;
	 
	 private String awardNo;

	    private Long awardMoney;

	    private Date createTime;

	    private Integer resId;

	    private String resType;

	    private Integer userId;
	    
	    private String awardStatus;

	    private String standby1;

	    private String standby2;
	    
	    private String nickname;
	    
	    private String phone;
	    
	    private String videoAudioTitle;
	    
	    private String videoAudioTypeName;
	    
	    private String headimgurl;
	    
	    private String openid;
	    
	    private String userName;
	    
	    
	    public String getUserName() {
			return userName;
		}

		public void setUserName(String userName) {
			this.userName = userName;
		}

		public String getOpenid() {
			return openid;
		}

		public void setOpenid(String openid) {
			this.openid = openid;
		}

		public String getAwardNo() {
			return awardNo;
		}

		public void setAwardNo(String awardNo) {
			this.awardNo = awardNo;
		}

		public String getAwardStatus() {
			return awardStatus;
		}

		public void setAwardStatus(String awardStatus) {
			this.awardStatus = awardStatus;
		}

		public String getHeadimgurl() {
			return headimgurl;
		}

		public void setHeadimgurl(String headimgurl) {
			this.headimgurl = headimgurl;
		}

		public String getPhone() {
			return phone;
		}

		public void setPhone(String phone) {
			this.phone = phone;
		}

		public String getVideoAudioTitle() {
			return videoAudioTitle;
		}

		public void setVideoAudioTitle(String videoAudioTitle) {
			this.videoAudioTitle = videoAudioTitle;
		}

		public String getVideoAudioTypeName() {
			return videoAudioTypeName;
		}

		public void setVideoAudioTypeName(String videoAudioTypeName) {
			this.videoAudioTypeName = videoAudioTypeName;
		}

		public String getNickname() {
			return nickname;
		}

		public void setNickname(String nickname) {
			this.nickname = nickname;
		}

		public Integer getAwardRecordId() {
	        return awardRecordId;
	    }

	    public void setAwardRecordId(Integer awardRecordId) {
	        this.awardRecordId = awardRecordId;
	    }

	    public Long getAwardMoney() {
	        return awardMoney;
	    }

	    public void setAwardMoney(Long awardMoney) {
	        this.awardMoney = awardMoney;
	    }

	    public Date getCreateTime() {
	        return createTime;
	    }

	    public void setCreateTime(Date createTime) {
	        this.createTime = createTime;
	    }

	    public Integer getResId() {
	        return resId;
	    }

	    public void setResId(Integer resId) {
	        this.resId = resId;
	    }

	    public String getResType() {
	        return resType;
	    }

	    public void setResType(String resType) {
	        this.resType = resType == null ? null : resType.trim();
	    }

	    public Integer getUserId() {
	        return userId;
	    }

	    public void setUserId(Integer userId) {
	        this.userId = userId;
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
