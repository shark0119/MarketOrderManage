package com.shark.entity;
/**
 * �û�ʵ����
 * @author Shark
 * id Ψһ��ʶ��
 * age ����
 * name �û���
 * pwd ����
 * sex �Ա�
 * mobile �绰����
 * address ��ַ
 * rid ��ɫid
 *  */
public class User {
	private Integer id, age, rid;
	private String name, pwd, sex, mobile, address;
	
	public User() {
	}
	public User(Integer id, Integer age, Integer rid, String name, String pwd, String sex, String mobile,
			String address) {
		super();
		this.id = id;
		this.age = age;
		this.rid = rid;
		this.name = name;
		this.pwd = pwd;
		this.sex = sex;
		this.mobile = mobile;
		this.address = address;
	}

	public Integer getRid() {
		return rid;
	}
	public void setRid(Integer rid) {
		this.rid = rid;
	}
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public Integer getAge() {
		return age;
	}
	public void setAge(Integer age) {
		this.age = age;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getPwd() {
		return pwd;
	}
	public void setPwd(String pwd) {
		this.pwd = pwd;
	}
	public String getSex() {
		return sex;
	}
	public void setSex(String sex) {
		this.sex = sex;
	}
	public String getMobile() {
		return mobile;
	}
	public void setMobile(String mobile) {
		this.mobile = mobile;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	@Override
	public String toString() {
		return "User [id=" + id + ", age=" + age + ", rid=" + rid + ", name=" + name + ", pwd=" + pwd + ", sex=" + sex
				+ ", mobile=" + mobile + ", address=" + address + "]";
	}
}
