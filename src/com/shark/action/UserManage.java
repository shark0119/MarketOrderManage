package com.shark.action;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.shark.entity.Pager;
import com.shark.entity.User;
import com.shark.service.UserService;

/**
 * Servlet implementation class UserManage
 */
@WebServlet(description = "用户管理", urlPatterns = { "/action/UserManage" })
public class UserManage extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private UserService us;
	private Pager pager;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public UserManage() {
		super();
		us = new UserService();
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.getSession().setAttribute("contentPageName", "userList.jsp");
		if (pager == null) {
			pager = new Pager();
		} else {
			String p = request.getParameter("pageIndex");
			String s = request.getParameter("pageSize");
			if (p != null) {
				pager.setPageIndex(Integer.parseInt(p));
				System.out.println("p is : " + p);
			}
			if (s != null) {
				pager.setPageSize(Integer.parseInt(s));
				System.out.println("s is : " + s);
			}
		}
		List<User> users = us.getUserList(pager);
		request.setAttribute("users", users);
		request.setAttribute("pager", pager);
		request.getRequestDispatcher("/jsp/main/mainPart.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

}
