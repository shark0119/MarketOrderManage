package com.shark.util;

import java.sql.ResultSet;
import java.sql.SQLException;

import com.shark.dao.BaseDao;
import com.shark.service.OrderService;
import com.shark.service.ProService;
import com.shark.service.RoleService;
import com.shark.service.UserService;
import com.shark.sql.GenerateSql;

public class CommonUtil {

	private static BaseDao  bd;
	private static UserService us;
	private static RoleService rs;
	private static ProService ps;
	private static OrderService os;
	static{
		bd = new BaseDao ();
		us = new UserService();
		rs = new RoleService();
		ps = new ProService();
		os = new OrderService();
	}
	private CommonUtil(){}
	/**
	 * 用于分页时查询总个数
	 * 由于在很多地方用到分页且操作相同，则编写了此工具类
	 * @param gs
	 * @return
	 */
	public static int getCount (GenerateSql gs){
		if (!bd.getConnection())
			return -1;
		ResultSet rset = bd.executeQuery(gs.getSql(), gs.getPara());
		try {
			if (rset.next()){
				return rset.getInt(1);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			bd.closeAll();
		}
		return 0;
	}
	
/*	public static String getRoleName (int id){
		if (!bd.getConnection())
			return null;
		String sql = " select rolename from mk_role where roleid= "+ id +" ";
		ResultSet rset = bd.executeQuery(sql);
		try {
			if (rset.next()){
				return rset.getString(1);
			}
		} catch (SQLException e) {
			e.printStackTrace();	
		} finally {
			bd.closeAll();
		}	
		return null;
	}*/
	
	public static UserService getUserService (){
		return us;
	}
	public static RoleService getRoleService (){
		return rs;
	}
	public static ProService getProService() {
		return ps;
	}
	public static OrderService getOrderService (){
		return os;
	}
	public static boolean isEmpty (String str){
		if (str == null || str.equals(""))
			return true;
		return false;
	}
}
