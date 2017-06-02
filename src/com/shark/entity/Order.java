package com.shark.entity;

import java.util.Date;

public class Order {
	private Integer id, ispay, money, count, c_pid;
	private Date ctime;
	private String desc;
	public Order() {
	}
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public Integer getIspay() {
		return ispay;
	}
	public void setIspay(Integer ispay) {
		this.ispay = ispay;
	}
	public Integer getMoney() {
		return money;
	}
	public void setMoney(Integer money) {
		this.money = money;
	}
	public Integer getCount() {
		return count;
	}
	public void setCount(Integer count) {
		this.count = count;
	}
	public Integer getC_pid() {
		return c_pid;
	}
	public void setC_pid(Integer c_pid) {
		this.c_pid = c_pid;
	}
	public Date getCtime() {
		return ctime;
	}
	public void setCtime(Date ctime) {
		this.ctime = ctime;
	}
	public String getDesc() {
		return desc;
	}
	public void setDesc(String desc) {
		this.desc = desc;
	}
	@Override
	public String toString() {
		return "Order [id=" + id + ", ispay=" + ispay + ", money=" + money + ", count=" + count + ", c_pid=" + c_pid
				+ ", ctime=" + ctime + ", desc=" + desc + "]";
	}
}
