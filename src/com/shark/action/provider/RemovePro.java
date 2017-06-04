package com.shark.action.provider;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.shark.util.CommonUtil;

@WebServlet("/pro/RemovePro")
public class RemovePro extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public RemovePro() {
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String id = request.getParameter("id");
		int pid = -1;
		if (!CommonUtil.isEmpty(id))
			pid = Integer.parseInt(id);
		if (CommonUtil.getOrderService().existNotDone(pid)) { // 无法删除
			response.getWriter().write("{\"success\": false,\"msg\": \"该供应商尚有未结算订单，无法删除\"}");
			return;
		}
		if (CommonUtil.getProService().removePro(pid)) // 删除成功
			response.getWriter().write("{\"success\": true,\"msg\": \"删除成功\"}");
		else // 数据库错误删除失败
			response.getWriter().write("{\"success\": false,\"msg\": \"数据库错误，无法删除\"}");
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

}
