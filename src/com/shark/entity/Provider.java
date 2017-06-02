package com.shark.entity;

import java.util.Date;

/**
 * ��Ӧ��ʵ����
 * id Ψһ��ʶ��
 * name ����
 * contact ��ϵ��ʽ
 * desc ��Ӧ������
 * phone �ֻ�����
 * address ��ַ
 * fax ����
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
