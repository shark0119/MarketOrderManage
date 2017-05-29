package com.shark.entity;

/**
 * 角色实体类
 * @author Shark
 *
 */
public class Role {
	private int rid;
	private String rname;
	public Role() {
	}
	public Role(int rid, String rname) {
		super();
		this.rid = rid;
		this.rname = rname;
	}
	public int getRid() {
		return rid;
	}
	public void setRid(int rid) {
		this.rid = rid;
	}
	public String getRname() {
		return rname;
	}
	public void setRname(String rname) {
		this.rname = rname;
	}
	@Override
	public String toString() {
		return "Role [rid=" + rid + ", rname=" + rname + "]";
	}
}
