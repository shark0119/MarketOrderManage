package com.shark.sql;

public interface GenerateSql {
	/**
	 * @return 获取PreparedStatement的预编译SQL语句
	 */
	String getSql ();
	/**
	 * @return 获取补位参数
	 */
	Object [] getPara ();
}
