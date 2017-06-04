package com.shark.action;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.shark.entity.User;
import com.shark.util.CommonUtil;

@WebServlet("/action/CheckUser")
public class CheckUser extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public CheckUser() {
        super();
    }
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String uid = request.getParameter("id");
		User user = null;
		try{
			user = CommonUtil.getUserService().getUser(Integer.parseInt(uid));
		}catch (Exception e){
			e.printStackTrace();
		}
		request.setAttribute("userDetail", user);
		request.getSession().setAttribute("contentPageName", "checkUser.jsp");
		request.getRequestDispatcher("/jsp/main/mainPart.jsp").forward(request, response);
	}
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
