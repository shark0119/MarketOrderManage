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
	}
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		condition = new User();
		//��̬����userList.jsp
		request.getSession().setAttribute("contentPageName", "userList.jsp");
		//��ʼ��pager
		if (pager == null) {
			pager = new Pager();
		} else {
			String p = request.getParameter("pageIndex");
			String s = request.getParameter("pageSize");
			String ss = request.getParameter("setPageSize");
			if (!CommonUtil.isEmpty(p)) {
				pager.setPageIndex(Integer.parseInt(p));
			}
			if (!CommonUtil.isEmpty(s)) {
				pager.setPageSize(Integer.parseInt(s));
			}
			if (!CommonUtil.isEmpty(ss)){
				pager.setPageSize(Integer.parseInt(ss));
			}
		}
		List<User> users ;
		//����������£���ȡ�û��б�
		String uname =request.getParameter("c_userName");
		if ( CommonUtil.isEmpty(uname) ){
			users = us.getUserList(pager);
		}else{		//����������£���ȡ�û��б�
			uname = new String(uname.getBytes("ISO-8859-1"), "UTF-8");
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
