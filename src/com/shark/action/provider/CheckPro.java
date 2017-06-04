package com.shark.action.provider;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.shark.entity.Provider;
import com.shark.util.CommonUtil;

@WebServlet("/pro/CheckPro")
public class CheckPro extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String pid = request.getParameter("id");
		Provider p = new Provider();
		if (!CommonUtil.isEmpty(pid))
			p = CommonUtil.getProService().getProvider(Integer.parseInt(pid));
		request.setAttribute("c_provider", p);
		request.getSession().setAttribute("contentPageName", "./provider/checkPro.jsp");
		request.getRequestDispatcher("/jsp/main/mainPart.jsp").forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

}
