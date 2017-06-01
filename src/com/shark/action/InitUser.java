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
import com.shark.util.CommonUtil;

@WebServlet(description = "�û�����", urlPatterns = { "/action/InitUser" })
public class InitUser extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private UserService us;
	private Pager pager;
	private User condition;
	public InitUser() {
		super();
		us = new UserService();
		condition = new User();
	}
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		//��̬����userList.jsp
		request.getSession().setAttribute("contentPageName", "userList.jsp");
		//��ʼ��pager
		if (pager == null) {
			pager = new Pager();
		} else {
			String p = request.getParameter("pageIndex");
			String s = request.getParameter("pageSize");
			if (p != null) {
				pager.setPageIndex(Integer.parseInt(p));
				//System.out.println("p is : " + p);
			}
			if (s != null) {
				pager.setPageSize(Integer.parseInt(s));
				//System.out.println("s is : " + s);
			}
		}
		List<User> users ;
		//����������£���ȡ�û��б�
		String uname =request.getParameter("c_userName");
		if ( CommonUtil.isEmpty(uname) ){
			users = us.getUserList(pager);
		}else{		//����������£���ȡ�û��б�
			condition.setName(uname);
			users = us.getUserList(pager, condition);
			request.setAttribute("condition", condition);
		}
		request.setAttribute("users", users);
		request.setAttribute("pager", pager);
		request.getRequestDispatcher("/jsp/main/mainPart.jsp").forward(request, response);
	}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

}
