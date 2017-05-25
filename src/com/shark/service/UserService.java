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
	 * @param user 包含用户有效信息的实体类对象，其ID和创建日期由数据库自动生成
	 * @return 成功返回插入的user对象 失败返回null
	 */
	public User addUser (User user){
		String sql = "{call addUser (?, ?, ?, ?, ?, ?)}";
		gs = new UserSql(sql, user.getUsername(), user.getPwd(), 
						user.getStatus(), user.getRole());
		User u1 = ud.addUser(gs);
		user.setId(u1.getId());
		user.setCreateTime(u1.getCreateTime());
		return user;
	}
	/**
	 * 获取全部用户集合
	 * @return 由结果集转化而来的实体集合,数据库失败返回null, 查询成功返回集合，集合元素个数为0表示无对应记录
	*/
	public List<User> getUserList (){
		String sql = " select * from tb_user ";
		return ud.getUserList(new UserSql (sql));
	}
	/**
	 * 分页查询时使用的用户集合获取
	 * @param pageIndex 当前页码
	 * @param pageSize 每一页显示的个数
	 * @return 由结果集转化而来的实体集合,数据库失败返回null, 查询成功返回集合，集合元素个数为0表示无对应记录
	 */
	public List<User> getUserList (int pageIndex, int pageSize){
		String sql = "select * from "+
				" (select rownum rn, t1.* from (select * from tb_user) t1) " +
				" where rn > ? and rn <= ? ";
		return ud.getUserList(new UserSql(sql, (pageIndex-1)*pageSize, pageIndex*pageSize));
	}
	/**
	 * 获取总的用户个数
	 * @return 用户的总数
	 */
	public int getUserCount (){
		String sql = "select count(id) from tb_user";
		return CommonUtil.getCount(new UserSql(sql));
	}
	/**
	 * 根据提供的用户ID来删除用户
	 * @return 成功为true 失败为FALSE
	 */
	public boolean deleteUser (int id){
		String sql = "delete from tb_user where id = ?";
		gs = new UserSql(sql, id);
		return -1 != ud.updateUser(gs);
	}
	/**
	 * 根据用户ID获取用户
	 * @param id
	 * @return 返回查询到的用户，失败返回null
	 */
	public User getUser (int id){
		String sql = "select * from tb_user where id = ?";
		gs = new UserSql(sql, id);
		return ud.getUser(gs);
	}
	/**
	 * 用户登录验证
	 * @param username
	 * @param pwd
	 * @return 成功返回true，失败返回FALSE
	 */
	public boolean loginVerify (String username, String pwd){
		String sql = "select * from tb_user where username=? and password = ? ";
		gs = new UserSql (sql, username, pwd);
		return ud.getUser(gs) != null;
	}
}
