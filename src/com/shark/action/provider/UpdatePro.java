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
 * ��Դ 1.�û��б������ 2.���ҳ�����ύ
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
				&& request.getParameter("operation").equals("update")) {// ��Դ2
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
			System.out.println("������ļ�¼:" + p);
			if (!CommonUtil.getProService().updatePro(p)) {
				// ����ɹ�
				response.getWriter().write(
						"<script>alert('���ʧ�ܣ����ݿ�������Ժ�����');location.href='/SuperMarket/jsp/main/mainPart.jsp';</script>");
				return;
			}
			response.sendRedirect("/SuperMarket/pro/InitPro");
		} else {// ��Դ1
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
