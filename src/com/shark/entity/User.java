package com.shark.entity;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * 用户实体类
 * @author Shark
 * id 唯一标识符
 * age 年龄
 * name 用户名
 * pwd 密码
 * sex 性别
 * mobile 电话号码
 * address 地址
 * rid 角色id
 * rname 角色名
 *  */
public class User {
	private Integer id, age, rid;
	private String name, pwd, sex, mobile, address, rname;
	private Date birth;
	public User() {
		id=-1;
		age = -1;
		rid = 2;
		name = pwd = sex = mobile = address = rname = "";
		try {
			birth = new SimpleDateFormat("yyyy-MM-dd").parse("2000-1-1");
		} catch (ParseException e) {
			birth = new Date();
		}
	}
	public User(Integer id, Date birth, Integer rid, String name, String pwd, String sex, String mobile,
			String address, String rname) {
		super();
		this.id = id;
		this.birth = birth;
		this.rid = rid;
		this.name = name;
		this.pwd = pwd;
		this.sex = sex;
		this.mobile = mobile;
		this.address = address;
		this.rname = rname;
	}
	public Date getBirth() {
		return birth;
	}
	public void setBirth(Date birth) {
		Calendar c = Calendar.getInstance();
		if (birth == null){
			c.set(2000, 1, 1); 
			birth = c.getTime();;
		}
		this.birth = birth;
		c.setTime(new Date());
		int today = c.get(Calendar.YEAR);
		c.setTime(birth);
		int birthday = c.get(Calendar.YEAR);
		this.age = today - birthday;
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
	public String getRname() {
		return rname;
	}
	public void setRname(String rname) {
		this.rname = rname;
	}
	@Override
	public String toString() {
		return "User [id=" + id + ", age=" + age + ", rid=" + rid + ", name=" + name + ", pwd=" + pwd + ", sex=" + sex
				+ ", mobile=" + mobile + ", address=" + address + ", rname=" + rname + ", birth=" + birth + "]";
	}

}
