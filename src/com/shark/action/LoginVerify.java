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
 * ��¼��֤����¼�ɹ��󣬻Ὣ��Ӧ����Ϣ����session��
 * �����Ƿ��¼
 * Login : string �ѵ�¼Ϊtrue
 * roleid: int ��ɫ����
 * id : int 	�û�ID
 * username: String �û�����
 * �ĸ��ֶ�
 */
@WebServlet(description = "���ε�¼��֤", urlPatterns = { "/LoginVerify" })
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
	 * ���ظ��ͻ���json�����ʽ�� { "success": true, "msg": "xxx" }
	 * success �Ƿ��¼�ɹ� msg��¼ʧ�ܰ�������Ϣ
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
				System.out.println("Ŀǰ����" + idSet.size()+"�˵�¼");
		} else{
			response.getWriter().write("{\"success\": false,\"msg\": \"�û������������!!!\"}");
		}
	}

}