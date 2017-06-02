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
		if (roleId == 1){ //��Ȩ��
			out.write("{\"success\": true,\"msg\": \"\"}");
		}else{ //��Ȩ��
			out.write("{\"success\": false,\"msg\": \"����Ȩ�޽��д˲���������ϵ����Ա����Ȩ��\"}");
		}
		out.flush();
		out.close();
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
