package com.huisou.po;

import java.util.Date;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Table(name = "busi_template_content")
public class TemplateContentPo {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer msgTemplateId;
	
    private String first;

    private String keyword1;

    private String keyword2;

    private String keyword3;

    private String keyword4;

    private String remark;
    
    private String url;
    
    private String exist;

    private Date createTime;

    private Date updateTime;

    public Integer getMsgTemplateId() {
        return msgTemplateId;
    }

    public void setMsgTemplateId(Integer msgTemplateId) {
        this.msgTemplateId = msgTemplateId;
    }

    public String getFirst() {
        return first;
    }

    public void setFirst(String first) {
        this.first = first == null ? null : first.trim();
    }

    public String getKeyword1() {
        return keyword1;
    }

    public void setKeyword1(String keyword1) {
        this.keyword1 = keyword1 == null ? null : keyword1.trim();
    }

    public String getKeyword2() {
        return keyword2;
    }

    public void setKeyword2(String keyword2) {
        this.keyword2 = keyword2 == null ? null : keyword2.trim();
    }

    public String getKeyword3() {
        return keyword3;
    }

    public void setKeyword3(String keyword3) {
        this.keyword3 = keyword3 == null ? null : keyword3.trim();
    }

    public String getKeyword4() {
        return keyword4;
    }

    public void setKeyword4(String keyword4) {
        this.keyword4 = keyword4 == null ? null : keyword4.trim();
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark == null ? null : remark.trim();
    }
    

    public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url == null ? null : url.trim();
	}

	public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getUpdateTime() {
        return updateTime;
    }
    

    public String getExist() {
		return exist;
	}

	public void setExist(String exist) {
		this.exist = exist == null ? null : exist.trim();
	}

	public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }
}