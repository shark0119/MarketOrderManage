package com.shark.service;

import java.util.List;

import com.shark.dao.UserDao;
import com.shark.dao.impl.UserDaoImpl;
import com.shark.entity.Pager;
import com.shark.entity.User;
import com.shark.sql.CommonSql;
import com.shark.sql.GenerateSql;
import com.shark.sql.UserSql;
import com.shark.util.CommonUtil;
import com.shark.util.MD5Util;

public class UserService {
	private GenerateSql gs = null;
	private UserDao ud = new UserDaoImpl();
	
	/**
	 * 增加用户
	 * @param user 包含用户有效信息的实体类对象，其ID和创建日期由数据库自动生成
	 * @return 成功返回插入的user对象 失败返回null
	 */
	public User addUser (User user){
		String sql = "{?=call addUser (?, ?, ?, ?, ?, ?, ?)}";
		gs = new UserSql(sql, user.getName(), MD5Util.string2MD5(user.getPwd()), user.getSex(), new java.sql.Date(user.getBirth().getTime()), user.getMobile(), user.getAddress(), user.getRid());
		User u1 = ud.addUser(gs);
		user.setId(u1.getId());
		return user;
	}
	/**
	 * 获取全部用户集合
	 * @return 由结果集转化而来的实体集合,数据库失败返回null, 查询成功返回集合，集合元素个数为0表示无对应记录
	*/
	public List<User> getUserList (){
		String sql = " select * from mk_user ";
		List<User> users = ud.getUserList(new UserSql (sql));
		for (User user: users){
			user.setRname(CommonUtil.getRoleService().getRoleName(user.getRid()));
		}
		return users;
	}
	/**
	 * 分页查询时使用的用户集合获取
	 * @param pageIndex 当前页码
	 * @param pageSize 每一页显示的个数
	 * @return 由结果集转化而来的实体集合,数据库失败返回null, 查询成功返回集合，集合元素个数为0表示无对应记录
	 */
	public List<User> getUserList (Pager pager){
		int pageIndex, pageSize;
		pageIndex = pager.getPageIndex();
		pageSize = pager.getPageSize();
		pager.setTotalCount(getUserCount());
		String sql = "select * from "+
				" (select rownum rn, t1.* from (select * from mk_user) t1) " +
				" where rn > ? and rn <= ? ";
		List<User> users = ud.getUserList(new UserSql (sql, (pageIndex-1)*pageSize, pageIndex*pageSize));
		for (User user: users){
			user.setRname(CommonUtil.getRoleService().getRoleName(user.getRid()));
		}
		return users;
	}
	/**
	 * 获取总的用户个数
	 * @return 用户的总数
	 */
	public int getUserCount (){
		String sql = "select count(id) from mk_user";
		return CommonUtil.getCount(new UserSql(sql));
	}
	/**
	 * 根据提供的用户ID来删除用户
	 * @return 成功为true 失败为FALSE
	 */
	public boolean deleteUser (int id){
		String sql = "delete from mk_user where id = ?";
		gs = new UserSql(sql, id);
		return -1 != ud.updateUser(gs);
	}
	/**
	 * 根据用户ID获取用户
	 * @param id
	 * @return 返回查询到的用户，失败返回null
	 */
	public User getUser (int id){
		String sql = "select * from mk_user where id = ?";
		gs = new UserSql(sql, id);
		User user = ud.getUser(gs);
		user.setRname(CommonUtil.getRoleService().getRoleName(user.getRid()));
		return user;
	}
	/**
	 * 用户登录验证
	 * @param username
	 * @param pwd
	 * @return 成功返回用户实例失败返回null
	 */
/*	public User loginVerify (String username, String pwd){
		String sql = "select * from mk_user where username=? and password = ? ";
		gs = new UserSql (sql, username, pwd);
		return ud.getUser(gs) ;
	}*/
	public User loginVerify (String username, String pwd){
		String sql = "select * from mk_user where username=? and password = ? ";
		gs = new UserSql (sql, username, pwd);
		User user = ud.getUser(gs);
		if (user != null){
			this.updateUser(user);
		}else if(null == user){
			user = ud.getUser(new CommonSql(sql, username, MD5Util.string2MD5(pwd)));
		} 
		return user;
	}
	/**
	 * 根据用户名查询用户
	 */
	public User getUser (String name){
		String sql = "select * from mk_user where username = ?";
		gs = new UserSql(sql, name);
		return ud.getUser(gs);
	}
	/**
	 * 根据条件来查询，获得用户列表
	 * @param pager 分页信息
	 * @param condition 包含条件字段的用户实体
	 * @return 用户列表
	 */
	public List<User> getUserList (Pager pager, User condition){
		int pageIndex, pageSize;
		pageIndex = pager.getPageIndex();
		pageSize = pager.getPageSize();
		pager.setTotalCount(CommonUtil.getCount(new UserSql(" select count(1) from mk_user where 1=1"+ "and username like '%"+condition.getName()+"%'" )));
		String sql = "select * from "+
				" (select rownum rn, t1.* from (select * from mk_user where 1=1"
				+ "and username like '%"+condition.getName()+"%'" +
				" ) t1)  where rn > ? and rn <= ? ";
		//条件字符串拼接
		List<User> users = ud.getUserList(new UserSql (sql, (pageIndex-1)*pageSize, pageIndex*pageSize));
		for (User user: users){
			user.setRname(CommonUtil.getRoleService().getRoleName(user.getRid()));
		}
		return users;
	}
	
	/**
	 * 更新用户，根据传入的User进行更新
	 * @return 成功返回true 失败返回FALSE
	 */
	public boolean updateUser(User user){
		String sql = 
				" update mk_user set username=?, password=?, sex=?, birth=?, mobile=?, address=?, roleid=? where id = ? ";
		if (-1 != ud.updateUser(new UserSql(sql, user.getName(), MD5Util.string2MD5(user.getPwd()), user.getSex(),
				new java.sql.Date(user.getBirth().getTime()), user.getMobile(), user.getAddress(), user.getRid(), user.getId() )))
			return true;
		return false;
	}
	/**
	 * 以用户名来判断数据库中是否存在该用户
	 * @param name 用户名
	 * @return 存在返回true 不存在返回false
	 */
	public boolean exists(String name) {
		String sql = "select * from mk_user where username=?";
		return null != ud.getUser(new CommonSql(sql, name));
	}
}
