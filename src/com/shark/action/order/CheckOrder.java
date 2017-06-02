package com.shark.action.order;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.shark.entity.Order;
import com.shark.entity.Product;
import com.shark.entity.Provider;
import com.shark.util.CommonUtil;

@WebServlet("/order/CheckOrder")
public class CheckOrder extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String pid = request.getParameter("id");
		Order o = CommonUtil.getOrderService().getOrder(Integer.parseInt(pid));
		Product p = CommonUtil.getProductService().getProductByC_Pid(o.getC_pid());
		Provider pro = CommonUtil.getProService().getProviderByC_Pid(o.getC_pid());
		request.setAttribute("product", p);
		request.setAttribute("provider", pro);
		request.setAttribute("c_order", o);
		request.getSession().setAttribute("contentPageName", "./order/checkOrder.jsp");
		request.getRequestDispatcher("/jsp/main/mainPart.jsp").forward(request, response);
	}
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
