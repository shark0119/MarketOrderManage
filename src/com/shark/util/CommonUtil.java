package com.shark.util;

import java.sql.ResultSet;
import java.sql.SQLException;

import com.shark.dao.BaseDao;
import com.shark.sql.GenerateSql;

public class CommonUtil {

	private CommonUtil(){}
	/**
	 * 用于分页时查询总个数
	 * 由于在很多地方用到分页且操作相同，则编写了此工具类
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
