package com.huisou.po;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
@Table(name = "busi_agent_copy")
public class AgentCopyPo {
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private Integer userId;

    private Integer grade;

    private Integer agentUserId;

    private Integer initialAgentUserId;

    private Integer classmateUserId;
    
    private Integer classEmpId;

    public Integer getClassEmpId() {
		return classEmpId;
	}

	public void setClassEmpId(Integer classEmpId) {
		this.classEmpId = classEmpId;
	}

	public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public Integer getGrade() {
        return grade;
    }

    public void setGrade(Integer grade) {
        this.grade = grade;
    }

    public Integer getAgentUserId() {
        return agentUserId;
    }

    public void setAgentUserId(Integer agentUserId) {
        this.agentUserId = agentUserId;
    }

    public Integer getInitialAgentUserId() {
        return initialAgentUserId;
    }

    public void setInitialAgentUserId(Integer initialAgentUserId) {
        this.initialAgentUserId = initialAgentUserId;
    }

    public Integer getClassmateUserId() {
        return classmateUserId;
    }

    public void setClassmateUserId(Integer classmateUserId) {
        this.classmateUserId = classmateUserId;
    }
}