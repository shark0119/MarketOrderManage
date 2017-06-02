package com.shark.action.order;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.shark.util.CommonUtil;

@WebServlet("/order/RemoveOrder")
public class RemoveOrder extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String id = request.getParameter("id");
		int pid = Integer.parseInt(id);
		if (CommonUtil.getOrderService().removeOrder(pid))	//ɾ���ɹ�
			response.getWriter().write("{\"success\": true,\"msg\": \"ɾ���ɹ�\"}");
		else	//���ݿ����ɾ��ʧ��
			response.getWriter().write("{\"success\": false,\"msg\": \"���ݿ�����޷�ɾ��\"}");
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}
}
