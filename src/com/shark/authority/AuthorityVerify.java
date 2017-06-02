package com.shark.authority;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/AuthorityVerify")
public class AuthorityVerify extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int roleId = (int) request.getSession().getAttribute("roleid");
		PrintWriter out = response.getWriter();
		if (roleId == 1){ //有权限
			out.write("{\"success\": true,\"msg\": \"\"}");
		}else{ //无权限
			out.write("{\"success\": false,\"msg\": \"您无权限进行此操作，请联系管理员升级权限\"}");
		}
		out.flush();
		out.close();
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
