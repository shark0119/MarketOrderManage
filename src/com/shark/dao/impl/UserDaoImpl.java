package com.shark.dao.impl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.shark.dao.BaseDao;
import com.shark.dao.UserDao;
import com.shark.entity.User;
import com.shark.sql.GenerateSql;

public class UserDaoImpl extends BaseDao implements UserDao {

	@Override
	public List<User> getUserList(GenerateSql gs) {
		if (!this.getConnection())
			return null;
		List<User> userList = null;
		ResultSet rset = this.executeQuery(gs.getSql(), gs.getPara());
		userList = rset2List(rset);
		this.closeAll();
		return userList;
	}
	/**
	 * @param rset �����
	 * @return �ɽ����ת��������ʵ�弯��,���ݿ�ʧ�ܷ���null, ��ѯ�ɹ����ؼ��ϣ�����Ԫ�ظ���Ϊ0��ʾ�޶�Ӧ��¼
	 */
	protected List<User> rset2List(ResultSet rset) {
		List <User> userList = null;
		User user;
		try {
			userList = new ArrayList <>();
			while (rset.next()){
				user = new User ();
				user.setId(rset.getInt("id"));
				user.setName(rset.getString("username"));
				user.setPwd(rset.getString("password"));
				user.setSex(rset.getString("sex"));
				user.setBirth(rset.getTimestamp("birth"));
				user.setMobile(rset.getString("mobile"));
				user.setAddress(rset.getString("address"));
				user.setRid(rset.getInt("roleid"));
				userList.add(user);
			}
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		} finally {
			this.closeAll();
		}
		return userList;
	}

	@Override
	public User getUser(GenerateSql gs) {
		if (!this.getConnection())
			return null;
		User user = null;
		ResultSet rset = this.executeQuery(gs.getSql(), gs.getPara());
		try {
			if (rset.next()) {
				user = new User();
				user.setId(rset.getInt("id"));
				user.setName(rset.getString("username"));
				user.setPwd(rset.getString("password"));
				user.setSex(rset.getString("sex"));
				user.setBirth(rset.getTimestamp("birth"));
				user.setMobile(rset.getString("mobile"));
				user.setAddress(rset.getString("address"));
				user.setRid(rset.getInt("roleid"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			this.closeAll();
		}
		return user;
	}

	@Override
	public int updateUser(GenerateSql gs) {
		if (!this.getConnection())
			return -1;
		int row = this.executeUpdate(gs.getSql(), gs.getPara());
		this.closeAll();
		return row;
	}
	@Override
	public User addUser(GenerateSql gs) {
		if (!this.getConnection())
			return null;
		User user = new User ();
		user= this.excuteAdd (gs.getSql(), user, gs.getPara());
		this.closeAll();
		return user;
	}

}
