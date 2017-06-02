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
@WebServlet("/pro/AddPro")
public class AddPro extends HttpServlet {
	private static final long serialVersionUID = 1L;
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		if (!CommonUtil.isEmpty(request.getParameter("operation")) && request.getParameter("operation").equals("add")){//��Դ2
			Provider p = new Provider();
			p.setName(request.getParameter("providerName"));
			p.setContact(request.getParameter("people"));
			p.setPhone(request.getParameter("phone"));
			p.setAddress(request.getParameter("address"));
			p.setFax(request.getParameter("fax"));
			p.setDesc(request.getParameter("describe"));
			System.out.println("������ļ�¼:"+p);
			if (!CommonUtil.getProService().addPro(p)){
				//����ɹ�
				response.getWriter().write("<script>alert('���ʧ�ܣ����ݿ�������Ժ�����');location.href='/SuperMarket/jsp/main/mainPart.jsp';</script>");
				return;
			}
			response.sendRedirect("/SuperMarket/pro/InitPro");
		}else {//��Դ1
			request.getSession().setAttribute("contentPageName", "./provider/proDetail.jsp");
			request.setAttribute("operation", "add");
			request.setAttribute("jumpPage", "AddPro");
			request.getRequestDispatcher("/jsp/main/mainPart.jsp").forward(request, response);
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

}
