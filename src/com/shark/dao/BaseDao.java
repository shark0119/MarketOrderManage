package com.shark.dao;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.util.ResourceBundle;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

import com.shark.entity.User;

public class BaseDao {
	
	private ResourceBundle rb=ResourceBundle.getBundle("database");
	
	Connection connection = null;
	PreparedStatement pstmt = null;
	ResultSet rs = null;
	
	/**
	 * 获取连接
	 * @return 普通jdbc连接方式连接数据库
	 */
	public boolean getConnection(){
		if (getConnection2()){
			//System.out.println("JNDI connect database");
			return true;
		}
		try {
			Class.forName(rb.getString("driver"));
			connection =DriverManager.getConnection(rb.getString("url"),rb.getString("username"),rb.getString("password"));
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
		return true;
	}
	
	/**
	 * 利用JNDI获取连接
	 * @return 连接成功返回true, 否则返回FALSE
	 */
	public boolean getConnection2(){
		try {
			Context context = new InitialContext();
			DataSource ds=(DataSource) context.lookup("java:comp/env/jdbc/SuperMarket");
			connection=ds.getConnection();
			return true;
		} catch (NamingException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return false;
	}
	
	/**
	 * @param sql PreparedStatement所需的预编译的sql语句
	 * @param params 待补全的参数
	 * @return 返回结果集
	 */
	public ResultSet executeQuery(String sql,Object...params){
		rs = null;
		try {
			pstmt=connection.prepareStatement(sql);
			for(int i=0;i<params.length;i++){
				pstmt.setObject(i+1, params[i]);
			}
			rs=pstmt.executeQuery();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return rs;
	}
	/**
	 * 执行删，改，增操作
	 * @return 数据库失败返回-1 否则返回受影响的行数
	 */
	public int executeUpdate(String sql,Object...params){
		int row=0;
		try {
			pstmt=connection.prepareStatement(sql);
			for(int i=0;i<params.length;i++){
				pstmt.setObject(i+1, params[i]);
			}
			row=pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
			return -1;
		}
		return row;
	}
	/**
	 * @param sql
	 * @param params
	 * @return 当前新增记录由数据库自动产生的主键，为Int类型
	 */
	public User excuteAdd (String sql , User user,Object...params){
		try {
			CallableStatement cstmt =connection.prepareCall(sql);
			cstmt.registerOutParameter(1, Types.INTEGER);
			for(int i=0;i<params.length;i++){
				cstmt.setObject(i+2, params[i]);
			}
			cstmt.execute();
			user.setId(cstmt.getInt(1));
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		} finally {
			this.closeAll();
		}
		return user;
	}
	/**
	 * 释放资源
	 */
	public void closeAll(){
		try {
			if(rs!=null) rs.close();
			if(pstmt!=null) pstmt.close();
			if(connection!=null) connection.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}

