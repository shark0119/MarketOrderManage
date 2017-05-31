package com.shark.service;

import java.util.List;

import com.shark.dao.UserDao;
import com.shark.dao.impl.UserDaoImpl;
import com.shark.entity.Pager;
import com.shark.entity.User;
import com.shark.sql.GenerateSql;
import com.shark.sql.UserSql;
import com.shark.util.CommonUtil;

public class UserService {
	private GenerateSql gs = null;
	private UserDao ud = new UserDaoImpl();
	
	/**
	 * �����û�
	 * @param user �����û���Ч��Ϣ��ʵ���������ID�ʹ������������ݿ��Զ�����
	 * @return �ɹ����ز����user���� ʧ�ܷ���null
	 */
	public User addUser (User user){
		String sql = "{?=call addUser (?, ?, ?, ?, ?, ?, ?)}";
		gs = new UserSql(sql, user.getName(), user.getPwd(), user.getSex(), new java.sql.Date(user.getBirth().getTime()), user.getMobile(), user.getAddress(), user.getRid());
		User u1 = ud.addUser(gs);
		user.setId(u1.getId());
		return user;
	}
	/**
	 * ��ȡȫ���û�����
	 * @return �ɽ����ת��������ʵ�弯��,���ݿ�ʧ�ܷ���null, ��ѯ�ɹ����ؼ��ϣ�����Ԫ�ظ���Ϊ0��ʾ�޶�Ӧ��¼
	*/
	public List<User> getUserList (){
		String sql = " select * from mk_user ";
		List<User> users = ud.getUserList(new UserSql (sql));
		for (User user: users){
			user.setRname(CommonUtil.getRoleName(user.getRid()));
		}
		return users;
	}
	/**
	 * ��ҳ��ѯʱʹ�õ��û����ϻ�ȡ
	 * @param pageIndex ��ǰҳ��
	 * @param pageSize ÿһҳ��ʾ�ĸ���
	 * @return �ɽ����ת��������ʵ�弯��,���ݿ�ʧ�ܷ���null, ��ѯ�ɹ����ؼ��ϣ�����Ԫ�ظ���Ϊ0��ʾ�޶�Ӧ��¼
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
			user.setRname(CommonUtil.getRoleName(user.getRid()));
		}
		return users;
	}
	/**
	 * ��ȡ�ܵ��û�����
	 * @return �û�������
	 */
	public int getUserCount (){
		String sql = "select count(id) from mk_user";
		return CommonUtil.getCount(new UserSql(sql));
	}
	/**
	 * �����ṩ���û�ID��ɾ���û�
	 * @return �ɹ�Ϊtrue ʧ��ΪFALSE
	 */
	public boolean deleteUser (int id){
		String sql = "delete from mk_user where id = ?";
		gs = new UserSql(sql, id);
		return -1 != ud.updateUser(gs);
	}
	/**
	 * �����û�ID��ȡ�û�
	 * @param id
	 * @return ���ز�ѯ�����û���ʧ�ܷ���null
	 */
	public User getUser (int id){
		String sql = "select * from mk_user where id = ?";
		gs = new UserSql(sql, id);
		return ud.getUser(gs);
	}
	/**
	 * �û���¼��֤
	 * @param username
	 * @param pwd
	 * @return �ɹ������û�ʵ��ʧ�ܷ���null
	 */
	public User loginVerify (String username, String pwd){
		String sql = "select * from mk_user where username=? and password = ? ";
		gs = new UserSql (sql, username, pwd);
		return ud.getUser(gs) ;
	}
	/**
	 * �����û�����ѯ�û�
	 */
	public User getUser (String name){
		String sql = "select * from mk_user where username = ?";
		gs = new UserSql(sql, name);
		return ud.getUser(gs);
	}
	/**
	 * ������������ѯ������û��б�
	 * @param pager ��ҳ��Ϣ
	 * @param user ���������ֶε��û�ʵ��
	 * @return �û��б�
	 */
	public List<User> getUserList (Pager pager, User user){
		int pageIndex, pageSize;
		pageIndex = pager.getPageIndex();
		pageSize = pager.getPageSize();
		pager.setTotalCount(getUserCount());
		String sql = "select * from "+
				" (select rownum rn, t1.* from (select * from mk_user where 1=1"
				+ "" +
				" ) t1)  where rn > ? and rn <= ? ";
		//�����ַ���ƴ��
		List<User> users = ud.getUserList(new UserSql (sql, (pageIndex-1)*pageSize, pageIndex*pageSize));
		for (User user1: users){
			user1.setRname(CommonUtil.getRoleName(user.getRid()));
		}
		return users;
	}
	
	/**
	 * �����û������ݴ����User���и���
	 * @return �ɹ�����true ʧ�ܷ���FALSE
	 */
	public boolean updateUser(User user){
		String sql = 
				" update mk_user set username=?, password=?, sex=?, birth=?, mobile=?, address=?, roleid=? where id = ? ";
		if (-1 != ud.updateUser(new UserSql(sql, user.getName(), user.getPwd(), user.getSex(),
				new java.sql.Date(user.getBirth().getTime()), user.getMobile(), user.getAddress(), user.getRid(), user.getId() )))
			return true;
		return false;
	}
}
