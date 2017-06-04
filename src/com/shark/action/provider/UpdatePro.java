package com.shark.action.provider;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.shark.entity.Provider;
import com.shark.util.CommonUtil;

/**
 * 来源 1.用户列表点击添加 2.添加页面点击提交
 * 
 * @author Shark
 *
 */
@WebServlet("/pro/UpdatePro")
public class UpdatePro extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		if (!CommonUtil.isEmpty(request.getParameter("operation"))
				&& request.getParameter("operation").equals("update")) {// 来源2
			Provider p = new Provider();
			try {
				p.setId(Integer.parseInt(request.getParameter("id")));
			} catch (Exception e) {
				e.printStackTrace();
				p.setId(-1);
			}
			p.setName(request.getParameter("providerName"));
			p.setContact(request.getParameter("people"));
			p.setPhone(request.getParameter("phone"));
			p.setAddress(request.getParameter("address"));
			p.setFax(request.getParameter("fax"));
			p.setDesc(request.getParameter("describe"));
			System.out.println("待插入的记录:" + p);
			if (!CommonUtil.getProService().updatePro(p)) {
				// 插入成功
				response.getWriter().write(
						"<script>alert('添加失败，数据库错误，请稍后再试');location.href='/SuperMarket/jsp/main/mainPart.jsp';</script>");
				return;
			}
			response.sendRedirect("/SuperMarket/pro/InitPro");
		} else {// 来源1
			request.getSession().setAttribute("contentPageName", "./provider/proDetail.jsp");
			request.setAttribute("operation", "update");
			request.setAttribute("jumpPage", "UpdatePro");
			String pid = request.getParameter("id");
			Provider p = CommonUtil.getProService().getProvider(Integer.parseInt(pid));
			request.setAttribute("u_provider", p);
			request.getRequestDispatcher("/jsp/main/mainPart.jsp").forward(request, response);
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

}
