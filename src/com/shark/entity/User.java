package com.shark.entity;

import java.util.Date;

/**
 * id 主键用户ID
 * username 用户姓名
 * pwd 用户密码
 * role 用户角色ID（管理员/普通）
 * createTime 注册时间
 * status 用户状态 （1.启用/禁用）
 * @author Shark
 */
public class User {
	private int id;
	private String username, pwd;
	private Date createTime;
	private int status, role;
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPwd() {
		return pwd;
	}
	public void setPwd(String pwd) {
		this.pwd = pwd;
	}
	public int getStatus() {
		return status;
	}
	public void setStatus(int status) {
		this.status = status;
	}
	public int getRole() {
		return role;
	}
	public void setRole(int role) {
		this.role = role;
	}
	public Date getCreateTime() {
		return createTime;
	}
	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}
	@Override
	public String toString() {
		return "User [id=" + id + ", username=" + username + ", pwd=" + pwd + ", status=" + status + ", role=" + role
				+ ", createTime=" + createTime + "]";
	}
}
