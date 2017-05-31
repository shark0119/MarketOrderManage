package com.shark.action;

import java.io.IOException;
import java.util.Set;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.shark.entity.User;
import com.shark.service.UserService;

/**
 * Servlet implementation class LoginVerify
 * 登录验证，登录成功后，会将相应的信息存入session中
 * 包括是否登录
 * Login : string 已登录为true
 * roleid: int 角色类型
 * id : int 	用户ID
 * username: String 用户姓名
 * 四个字段
 */
@WebServlet(description = "初次登录验证", urlPatterns = { "/LoginVerify" })
public class LoginVerify extends HttpServlet {
	private static final long serialVersionUID = 1L;
	public LoginVerify() {
		super();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.getWriter().write("must post");
	}

	/**
	 * 返回给客户端json代码格式是 { "success": true, "msg": "xxx" }
	 * success 是否登录成功 msg登录失败包含的信息
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		UserService us = new UserService();
		User user = us.loginVerify(request.getParameter("username"), request.getParameter("pwd"));
		if (user != null) {
				response.getWriter().write("{\"success\": true,\"msg\": \"\"}");
				HttpSession session = request.getSession();
				session.setAttribute("roleid", user.getRid());
				session.setAttribute("id", user.getId());
				session.setAttribute("username", user.getName());
				session.setAttribute("login", "true");
				session.setAttribute("contentPageName", "welcome.jsp");
				ServletContext application = session.getServletContext();
				@SuppressWarnings("unchecked")
				Set<Integer> idSet = (Set<Integer>) application.getAttribute("logedId");
				idSet.add(user.getId());
				System.out.println("目前已有" + idSet.size()+"人登录");
		} else{
			response.getWriter().write("{\"success\": false,\"msg\": \"用户名或密码错误!!!\"}");
		}
	}

}