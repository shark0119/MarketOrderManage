package com.shark.util;

import java.sql.ResultSet;
import java.sql.SQLException;

import com.shark.dao.BaseDao;
import com.shark.sql.GenerateSql;

public class CommonUtil {

	private static BaseDao  bd;
	static{
		bd = new BaseDao ();
	}
	private CommonUtil(){}
	/**
	 * ���ڷ�ҳʱ��ѯ�ܸ���
	 * �����ںܶ�ط��õ���ҳ�Ҳ�����ͬ�����д�˴˹�����
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
	
	public static String getRoleName (int id){
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
	}
}
