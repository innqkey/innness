package com.huisou.po;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Table(name = "busi_manager")
public class ManagerPo {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer managerId;

    private String managerName;

    private String password;

    private Integer managerStatus;

    private String managerPetname;

    private String managerLogo;

    public Integer getManagerId() {
        return managerId;
    }

    public void setManagerId(Integer managerId) {
        this.managerId = managerId;
    }

    public String getManagerName() {
        return managerName;
    }

    public void setManagerName(String managerName) {
        this.managerName = managerName == null ? null : managerName.trim();
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password == null ? null : password.trim();
    }

    public Integer getManagerStatus() {
        return managerStatus;
    }

    public void setManagerStatus(Integer managerStatus) {
        this.managerStatus = managerStatus;
    }

    public String getManagerPetname() {
        return managerPetname;
    }

    public void setManagerPetname(String managerPetname) {
        this.managerPetname = managerPetname == null ? null : managerPetname.trim();
    }

    public String getManagerLogo() {
        return managerLogo;
    }

    public void setManagerLogo(String managerLogo) {
        this.managerLogo = managerLogo == null ? null : managerLogo.trim();
    }
}