package com.shark.service;

import java.util.List;

import com.shark.dao.RoleDao;
import com.shark.dao.impl.RoleDaoImpl;
import com.shark.entity.Role;

public class RoleService {
	private RoleDao rd= null;
	public RoleService (){
		rd = new RoleDaoImpl();
	}
	public List<Role> getRoleList (){
		return rd.getRoleList();
	}
	public String getRoleName(int id) {
		return rd.getRoleName(id);
	}
}
