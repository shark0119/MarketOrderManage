package com.shark.action.order;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.shark.util.CommonUtil;
import com.shark.util.OtherServiceUtil;

/**
 * ajax获取单价
 * @author Shark
 *
 */
@WebServlet("/order/GetPrice")
public class GetPrice extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String productId = request.getParameter("productId");
		String providerId = request.getParameter("providerId");
		int price = -1;
		if (!CommonUtil.isEmpty(providerId) && !CommonUtil.isEmpty(productId))
			price = OtherServiceUtil.getPriceById(Integer.parseInt(providerId), Integer.parseInt(productId));
		System.out.println("单价为:"+price);
		PrintWriter out = response.getWriter();
		out.write(Integer.toString(price));
		out.flush();
		out.close();
	}
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}
}
