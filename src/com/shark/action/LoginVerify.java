package com.shark.action;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.shark.service.UserService;

/**
 * Servlet implementation class LoginVerify
 */
@WebServlet(description = "µÇÂ¼ÑéÖ¤", urlPatterns = { "/action/LoginVerify" })
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
		if (us.loginVerify(request.getParameter("username"), request.getParameter("pwd")))
			response.getWriter().write("true");
		else
			response.getWriter().write("false");
	}

}
