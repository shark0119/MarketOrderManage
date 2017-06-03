package test;

import java.sql.Connection;
import java.sql.DriverManager;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

import com.shark.entity.Pager;
import com.shark.entity.User;
import com.shark.service.UserService;

public class UserServiceTest {

	private UserService us;

	@Before
	public void init() {
		us = new UserService();
	}

	@Test
	public void testAddUser() {
		User user = new User();
		user.setName("hahaha");
		user.setPwd("asdf1234");
		user.setAddress("nowhere");
		Calendar calendar = Calendar.getInstance();
		calendar.set(2015, 10, 12); 
		Date date = calendar.getTime();
		user.setBirth(date);
		user.setMobile("12312389078");
		user.setRid(2);
		user.setSex("ÄÐ");
		user = us.addUser(user);
		System.out.println(user);
	}

	@Test
	public void testUserCount() {
		System.out.println(us.getUserCount());
	}

	@Test
	public void testGetUser() {
		User user = us.getUser(18);
		System.out.println(user);
	}

	@Test
	public void testDeleteUser() {
		System.out.println(us.deleteUser(17));
	}

	@Test
	public void testGetUserList() {
		List<User> ul = us.getUserList();
		for (User user : ul) {
			System.out.println(user);
		}
	}

	@Test
	public void testPage() {
		Pager pager = new Pager(1, 2, 0, 1);
		List<User> ul = us.getUserList(pager);
		for (User user : ul) {
			System.out.println(user);
		}
	}

	@Test
	public void testLoginVerify() {
		System.out.println(us.loginVerify("admin", "123"));
	}
	
	@Test
	public void testAddDate () throws Exception{
		Class.forName("oracle.jdbc.driver.OracleDriver");
		String url = "jdbc:oracle:thin:@127.0.0.1:1521:orcl";
		Connection conn = DriverManager.getConnection(url, "mk", "123");
		Date date  = new Date();
		SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");  
		date = sdf.parse("2000-11-19");
		System.out.println(date);
		String sql = "insert into dateTest values ( To_date('"+date+"','yyyy-mm-dd')"+")";
		System.out.println(sql);
		conn.createStatement().executeUpdate(sql);
		conn.close();
	}
	@Test
	public void testDateTran()throws Exception{
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		Date date = sdf.parse("1029-12-18");
		System.out.println(date);
		
	}
	
	@Test
	public void testUpdateUser (){
		User user = us.getUser(32);
		user.setSex("Å®");
		System.out.println(user);
		System.out.println(us.updateUser(user));
	}
	@Test
	public void testNameAvail (){
		System.out.println(us.exists("admin"));
	}
}
