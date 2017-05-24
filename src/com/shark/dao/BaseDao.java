package com.shark.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

public class BaseDao {
	
	private ResourceBundle rb=ResourceBundle.getBundle("database");
	
	Connection connection = null;
	PreparedStatement pstmt = null;
	ResultSet rs = null;
	
	/**
	 * ��ȡ����
	 * @return ��ͨjdbc���ӷ�ʽ�������ݿ�
	 */
	public boolean getConnection(){
		try {
			System.out.println(rb.getString("driver"));
			Class.forName(rb.getString("driver"));
			
			connection =DriverManager.getConnection(rb.getString("url"),rb.getString("username"),rb.getString("password"));
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
		return true;
	}
	
	/**
	 * ����JNDI��ȡ����
	 * @return ���ӳɹ�����true, ���򷵻�FALSE
	 */
	public boolean getConnection2(){
		try {
			Context context = new InitialContext();
			DataSource ds=(DataSource) context.lookup("java:comp/env/jdbc/news");
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
	 * @param sql PreparedStatement�����Ԥ�����sql���
	 * @param params ����ȫ�Ĳ���
	 * @return ���ؽ����
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
	 * ִ��ɾ���ģ�������
	 * @return ���ݿ�ʧ�ܷ���-1 ���򷵻���Ӱ�������
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
	 * �ͷ���Դ
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

