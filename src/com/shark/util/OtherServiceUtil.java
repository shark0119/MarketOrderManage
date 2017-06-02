package com.shark.util;

import java.sql.ResultSet;
import java.sql.SQLException;

import com.shark.dao.BaseDao;

public class OtherServiceUtil {
	private static BaseDao bd;
	static {
		bd = new BaseDao ();
	}
	private OtherServiceUtil (){}
	/**
	 * ͨ����Ʒ����ȡ��ƷID
	 * @param name ��Ʒ��
	 * @return ��Ӧ��ID
	 */
	public static int getProductIdByName (String name){
		String sql = " select id from mk_product where name=' "+name+"'";
		if ( !bd.getConnection() ){
			return -1;
		}
		ResultSet rset = bd.executeQuery(sql);
		try {
			if (rset.next()){
				return rset.getInt("id");
			}
		} catch (SQLException e) {
			e.printStackTrace();
			return -1;
		}
		return 0;
	}
	/**
	 * ͨ����Ʒ����ȡ��ƷID
	 * @param name ��Ʒ��
	 * @return ��Ӧ��ID
	 */
	public static int getProviderIdByName (String name){
		String sql = " select id from mk_provider where name=' "+name+"'";
		if ( !bd.getConnection() ){
			return -1;
		}
		ResultSet rset = bd.executeQuery(sql);
		try {
			if (rset.next()){
				return rset.getInt("id");
			}
		} catch (SQLException e) {
			e.printStackTrace();
			return -1;
		}
		return 0;
	}
}
