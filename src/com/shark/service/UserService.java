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
	 * 增加用户
	 * @param user
	 * @return 成功返回true 失败返回FALSE
	 */
	public boolean addUser (User user){
		String sql = "insert into tb_user (id, username, password, status, createTime, roleid) values (?,?,?,?,?,?);";
		gs = new UserSql(sql, user.getId(),
				user.getUsername(), user.getPwd(), user.getStatus(), user.getCreateTime(), user.getRole());
		return -1 != ud.updateUser(gs);
	}
	/**
	 * 获取全部用户集合
	 * @return
	 */
	public List<User> getUserList (){
		
		return null;
	}
	/**
	 * 分页查询时使用的用户集合获取
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
	 * 获取总的用户个数
	 * @return
	 */
	public int getUserCount (){
		String sql = "select count(id) from tb_user;";
		return CommonUtil.getCount(new UserSql(sql));
	}
	/**
	 * 根据提供的用户ID来删除用户
	 */
	public boolean deleteUser (int id){
		String sql = "delete from tb_user where id = ?;";
		gs = new UserSql(sql, id);
		return -1 != ud.updateUser(gs);
	}
	/**
	 * 根据用户ID获取用户
	 * @param id
	 * @return
	 */
	public User getUser (int id){
		String sql = "select * from tb_user where id = ?;";
		gs = new UserSql(sql, id);
		return ud.getUser(gs);
	}
}
