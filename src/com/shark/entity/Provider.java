package com.shark.entity;

import java.util.Date;

/**
 * 供应商实体类
 * id 唯一标识符
 * name 名称
 * contact 联系方式
 * desc 供应商描述
 * phone 手机号码
 * address 地址
 * fax 传真
 * @author Shark
 *
 */
public class Provider {
	private Integer id;
	private String name, contact, desc, phone, address, fax;
	private Date cdate;


	public Provider() {	}
	public Date getCdate() {
		return cdate;
	}
	public void setCdate(Date cdate) {
		this.cdate = cdate;
	}
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getContact() {
		return contact;
	}
	public void setContact(String contact) {
		this.contact = contact;
	}
	public String getDesc() {
		return desc;
	}
	public void setDesc(String desc) {
		this.desc = desc;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getFax() {
		return fax;
	}
	public void setFax(String fax) {
		this.fax = fax;
	}
	@Override
	public String toString() {
		return "Provider [id=" + id + ", name=" + name + ", contact=" + contact + ", desc=" + desc + ", phone=" + phone
				+ ", address=" + address + ", fax=" + fax + ", cdate=" + cdate + "]";
	}

}
