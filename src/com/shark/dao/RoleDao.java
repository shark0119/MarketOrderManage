package com.shark.dao;

import java.util.List;

import com.shark.entity.Role;

public interface RoleDao {
	/**
	 * 
	 * @return 返回角色列表，数据库错误返回null, 查询成功返回集合
	 */
	List<Role> getRoleList();
}
