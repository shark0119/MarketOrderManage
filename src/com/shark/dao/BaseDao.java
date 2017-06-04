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
	 * ��ȡ����
	 * @return ��ͨjdbc���ӷ�ʽ�������ݿ�
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
	 * ����JNDI��ȡ����
	 * @return ���ӳɹ�����true, ���򷵻�FALSE
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
	 * @param sql
	 * @param params
	 * @return ��ǰ������¼�����ݿ��Զ�������������ΪInt����
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

