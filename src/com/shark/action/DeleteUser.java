package com.shark.action;

import java.io.IOException;
import java.util.HashSet;
import java.util.Set;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.shark.util.CommonUtil;

@WebServlet("/action/DeleteUser")
public class DeleteUser extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public DeleteUser() {
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doPost(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String id =request.getParameter("id");
		int uid = Integer.parseInt(id);
		@SuppressWarnings("unchecked")
		Set<Integer> idSet = (HashSet<Integer>) request.getSession().getServletContext().getAttribute("logedId");
		if (idSet.contains(uid)) {
			response.getWriter().write("{\"success\": false,\"msg\": \"���û���������ʹ��ϵͳ�޷�ɾ��\"}");
			return;
		}
		if (CommonUtil.getUserService().deleteUser(uid))
			response.getWriter().write("{\"success\": true,\"msg\": \"ɾ���ɹ�\"}");
		else
			response.getWriter().write("{\"success\": false,\"msg\": \"���ݿ�����޷�ɾ��\"}");
	}
}
