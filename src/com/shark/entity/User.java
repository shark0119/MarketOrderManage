package com.shark.entity;

import java.util.Date;

/**
 * id �����û�ID
 * username �û�����
 * pwd �û�����
 * role �û���ɫID������Ա/��ͨ��
 * createTime ע��ʱ��
 * status �û�״̬ ��1.����/���ã�
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
