package com.shark.util;

import java.sql.ResultSet;
import java.sql.SQLException;

import com.shark.dao.BaseDao;
import com.shark.sql.GenerateSql;

public class CommonUtil {

	private CommonUtil(){}
	/**
	 * ���ڷ�ҳʱ��ѯ�ܸ���
	 * �����ںܶ�ط��õ���ҳ�Ҳ�����ͬ�����д�˴˹�����
	 * @param gs
	 * @return
	 */
	public static int getCount (GenerateSql gs){
		BaseDao bd = new BaseDao ();
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
}
