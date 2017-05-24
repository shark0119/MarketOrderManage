package com.shark.service;

import java.util.List;

import com.shark.dao.UserDao;
import com.shark.dao.impl.UserDaoImpl;
import com.shark.entity.User;
import com.shark.sql.GenerateSql;
import com.shark.sql.UserSql;
import com.shark.util.CommonUtil;

public class UserService {
	private GenerateSql gs = null;
	private UserDao ud = new UserDaoImpl();
	
	/**
	 * �����û�
	 * @param user
	 * @return �ɹ�����true ʧ�ܷ���FALSE
	 */
	public boolean addUser (User user){
		String sql = "insert into tb_user (id, username, password, status, createTime, roleid) values (?,?,?,?,?,?);";
		gs = new UserSql(sql, user.getId(),
				user.getUsername(), user.getPwd(), user.getStatus(), user.getCreateTime(), user.getRole());
		return -1 != ud.updateUser(gs);
	}
	/**
	 * ��ȡȫ���û�����
	 * @return
	 */
	public List<User> getUserList (){
		
		return null;
	}
	/**
	 * ��ҳ��ѯʱʹ�õ��û����ϻ�ȡ
	 * @param pageIndex
	 * @param pageSize
	 * @return
	 */
	public List<User> getUserList (int pageIndex, int pageSize){
		String sql = "select * from "+
				" (select rownum rn, t1.* from (select * from tb_user) t1) " +
				" where rn > ? and rn <= ?; ";
		return ud.getUserList(new UserSql(sql, (pageIndex-1)*pageSize, pageIndex*pageSize));
	}
	/**
	 * ��ȡ�ܵ��û�����
	 * @return
	 */
	public int getUserCount (){
		String sql = "select count(id) from tb_user;";
		return CommonUtil.getCount(new UserSql(sql));
	}
	/**
	 * �����ṩ���û�ID��ɾ���û�
	 */
	public boolean deleteUser (int id){
		String sql = "delete from tb_user where id = ?;";
		gs = new UserSql(sql, id);
		return -1 != ud.updateUser(gs);
	}
	/**
	 * �����û�ID��ȡ�û�
	 * @param id
	 * @return
	 */
	public User getUser (int id){
		String sql = "select * from tb_user where id = ?;";
		gs = new UserSql(sql, id);
		return ud.getUser(gs);
	}
}
