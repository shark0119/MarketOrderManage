package com.shark.action;

import java.io.IOException;
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
 */
@WebServlet(description = "登录验证", urlPatterns = { "/action/LoginVerify" })
public class LoginVerify extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public LoginVerify() {
        super();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.getWriter().write("must post");
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		UserService us = new UserService();
		User user = us.loginVerify(request.getParameter("username"), request.getParameter("pwd"));
		if (user != null){
			if (user.getStatus() == 2)
				response.getWriter().write("{\"success\": false,\"msg\": \"用户已被禁用，请联系管理员\"}");
			else{
				response.getWriter().write("{\"success\": true,\"msg\": \"\"}");
				HttpSession session = request.getSession();
				session.setAttribute("roleid", user.getRole());
				session.setAttribute("id", user.getId());
				session.setAttribute("username", user.getUsername());
			}
		}else
			response.getWriter().write("{\"success\": false,\"msg\": \"用户名或密码错误!!!\"}");
	}

}
