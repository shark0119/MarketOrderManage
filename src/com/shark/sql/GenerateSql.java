package com.shark.sql;

public interface GenerateSql {
	/**
	 * @return ��ȡPreparedStatement��Ԥ����SQL���
	 */
	String getSql ();
	/**
	 * @return ��ȡ��λ����
	 */
	Object [] getPara ();
}
