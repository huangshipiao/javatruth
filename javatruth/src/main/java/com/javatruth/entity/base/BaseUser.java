package com.javatruth.entity.base;

import java.util.Date;

import com.common.persistence.bean.BaseEntity;
import com.javatruth.entity.User;

public class BaseUser extends BaseEntity<User> {
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private Long id;

    private String userName;

    private String nickName;

    private String userPwd;

    private Integer status;

    private String email;

    private String mobile;

    private Integer userType;

    private String headImg;

    private Integer sex;

    private Date createTime;

    private Date lastLogin;

    private String lastIp;

    private Integer loginNum;

    private Date upateTime;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName == null ? null : userName.trim();
    }

    public String getNickName() {
        return nickName;
    }

    public void setNickName(String nickName) {
        this.nickName = nickName == null ? null : nickName.trim();
    }

    public String getUserPwd() {
        return userPwd;
    }

    public void setUserPwd(String userPwd) {
        this.userPwd = userPwd == null ? null : userPwd.trim();
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email == null ? null : email.trim();
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile == null ? null : mobile.trim();
    }

    public Integer getUserType() {
        return userType;
    }

    public void setUserType(Integer userType) {
        this.userType = userType;
    }

    public String getHeadImg() {
        return headImg;
    }

    public void setHeadImg(String headImg) {
        this.headImg = headImg == null ? null : headImg.trim();
    }

    public Integer getSex() {
        return sex;
    }
    
    public void setSex(Integer sex) {
        this.sex = sex;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getLastLogin() {
        return lastLogin;
    }

    public void setLastLogin(Date lastLogin) {
        this.lastLogin = lastLogin;
    }

    public String getLastIp() {
        return lastIp;
    }

    public void setLastIp(String lastIp) {
        this.lastIp = lastIp == null ? null : lastIp.trim();
    }

    public Integer getLoginNum() {
        return loginNum;
    }

    public void setLoginNum(Integer loginNum) {
        this.loginNum = loginNum;
    }

    public Date getUpateTime() {
        return upateTime;
    }

    public void setUpateTime(Date upateTime) {
        this.upateTime = upateTime;
    }

	@Override
	public String toString() {
		return "BaseUser [id=" + id + ", userName=" + userName + ", nickName=" + nickName + ", userPwd=" + userPwd
				+ ", status=" + status + ", email=" + email + ", mobile=" + mobile + ", userType=" + userType
				+ ", headImg=" + headImg + ", sex=" + sex + ", createTime=" + createTime + ", lastLogin=" + lastLogin
				+ ", lastIp=" + lastIp + ", loginNum=" + loginNum + ", upateTime=" + upateTime + "]";
	}


}