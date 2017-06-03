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

@WebServlet("/ExitSystem")
public class ExitSystem extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		Integer id = (Integer) session.getAttribute("id");
		session.removeAttribute("roleid");
		session.removeAttribute("id");
		session.removeAttribute("username");
		session.removeAttribute("login");
		session.removeAttribute("contentPageName");
		ServletContext application = session.getServletContext();
		@SuppressWarnings("unchecked")
		Set<Integer> idSet = (Set<Integer>) application.getAttribute("logedId");
		idSet.remove(id);
		System.out.println("用户 "+id+" 已下线");
		response.sendRedirect("/SuperMarket/jsp/login.jsp");
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
