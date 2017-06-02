package com.shark.sql;

public class CommonSql  implements GenerateSql{
	private String sql;
	private Object [] para;
	public CommonSql(String sql, Object...para) {
		this.sql = sql;
		this.para = para;
	}
	@Override
	public String getSql() {
		return sql;
	}
	@Override
	public Object[] getPara() {
		return para;
	}
}