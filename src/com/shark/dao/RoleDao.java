package com.shark.dao;

import java.util.List;

import com.shark.entity.Role;

public interface RoleDao {
	/**
	 * 
	 * @return ���ؽ�ɫ�б����ݿ���󷵻�null, ��ѯ�ɹ����ؼ���
	 */
	List<Role> getRoleList();
}
