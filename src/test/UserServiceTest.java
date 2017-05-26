package test;

import java.util.List;

import org.junit.Before;
import org.junit.Test;

import com.shark.entity.Pager;
import com.shark.entity.User;
import com.shark.service.UserService;

public class UserServiceTest {

	private UserService us;
	@Before
	public void init (){
		us = new UserService ();
	}
	@Test
	public void testAddUser (){
		User user = new User ();
		user.setUsername("hahaha");
		user.setPwd("asdf1234");
		user.setRole(1);
		user.setStatus(2);
		user = us.addUser(user);
		System.out.println(user);
	}
	@Test
	public void testUserCount() {
		System.out.println(us.getUserCount());
	}
	@Test
	public void testGetUser (){
		User user  = us.getUser(13);
		System.out.println(user);
	}
	@Test 
	public void testDeleteUser (){
		System.out.println(us.deleteUser(12));
	}
	@Test
	public void testGetUserList (){
		List <User> ul = us.getUserList();
		for (User user: ul){
			System.out.println(user);
		}
	}
	@Test
	public void testPage (){
		Pager pager = new Pager(1, 4, 0, 1);
		List <User> ul = us.getUserList(pager);
		for (User user: ul){
			System.out.println(user);
		}
	}
	@Test
	public void testLoginVerify (){
		System.out.println(us.loginVerify("ÀîËÄ", "123"));
	}

}
