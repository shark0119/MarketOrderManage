package com.shark.action;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet("/ExitSystem")
public class ExitSystem extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public ExitSystem() {
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		session.removeAttribute("roleid");
		session.removeAttribute("id");
		session.removeAttribute("username");
		session.removeAttribute("login");
		session.removeAttribute("contentPageName");
		response.sendRedirect("/SuperMarket/jsp/login.jsp");
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
