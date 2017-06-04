package com.shark.action;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.shark.entity.Role;
import com.shark.entity.User;
import com.shark.util.CommonUtil;

/**
 * 可能由两个地方跳转而来
 * 1.用户详情表通过点击添加按钮
 * 2.添加用户后提交到此处
 * Servlet implementation class AddUser
 */
@WebServlet("/action/AddUser")
public class AddUser extends HttpServlet {
	private static final long serialVersionUID = 1L;
    public AddUser() {
        super();
    }
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		if (request.getParameter("add") != null || "add".equals(request.getParameter("add"))){
			User user = new User();
			user.setAddress(request.getParameter("address"));
			try {
				user.setBirth(new SimpleDateFormat("yyyy-MM-dd").parse(request.getParameter("date")));
			} catch (ParseException e) {
				e.printStackTrace();
				response.getWriter().write("<script>alert('添加失败,请稍后再试');location.href='/SuperMarket/jsp/main/mainPart.jsp';</script>");
				return;
			}
			user.setMobile(request.getParameter("phone"));
			user.setName(request.getParameter("userName"));
			user.setPwd(request.getParameter("password"));
			try{
				user.setRid(Integer.parseInt(request.getParameter("role")));
			}catch (Exception e){
				e.printStackTrace();
			}
			user.setSex(request.getParameter("sex"));
			if (null == CommonUtil.getUserService().addUser(user)){
				response.getWriter().write("<script>alert('添加失败，数据库错误，请稍后再试');location.href='/SuperMarket/jsp/main/mainPart.jsp';</script>");
				return;
			}
			response.sendRedirect("/SuperMarket/action/InitUser");
		}else{//由用户详情页跳转而来
			request.getSession().setAttribute("contentPageName", "UserAdd.jsp");
			List <Role> roleList = CommonUtil.getRoleService().getRoleList();
			request.setAttribute("roleList", roleList);
			request.getRequestDispatcher("/jsp/main/mainPart.jsp").forward(request, response);
		}
	}
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
