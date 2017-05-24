package com.shark.sql;

public class UserSql implements GenerateSql{
	private String sql;
	private Object [] para;
	public UserSql(String sql, Object...para) {
		super();
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
