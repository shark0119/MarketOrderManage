package com.shark.action.order;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.shark.entity.Order;
import com.shark.entity.Pager;
import com.shark.entity.Product;
import com.shark.entity.Provider;
import com.shark.service.OrderService;
import com.shark.util.CommonUtil;

@WebServlet("/order/InitOrder")
public class InitOrder extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private Pager pager = null;
	private Order condition;
	private OrderService os;

    public InitOrder() {
    	os = CommonUtil.getOrderService();
    }
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	condition = new Order();
		request.getSession().setAttribute("contentPageName", "./order/orderList.jsp");
		condition = new Order();
		// 初始化pager
		if (pager == null) {
			pager = new Pager();
		} else {
			String p = request.getParameter("pageIndex");
			String s = request.getParameter("pageSize");
			String ss = request.getParameter("setPageSize");
			if (p != null) {
				pager.setPageIndex(Integer.parseInt(p));
			}
			if (s != null) {
				pager.setPageSize(Integer.parseInt(s));
			}
			if (!CommonUtil.isEmpty(ss)){
				pager.setPageSize(Integer.parseInt(ss));
			}
		}
		String productId = request.getParameter("s_productId");
		String providerId = request.getParameter("s_providerId");
		String status = request.getParameter("s_status");
		List<Order> oList = null;
		int i_p1 =-1, i_p2 =-1;
		if (!CommonUtil.isEmpty(productId)) {// 有商品名称条件时分页查询
			i_p1 = Integer.parseInt(productId);
			request.setAttribute("productId", i_p1);
		}
		if(!CommonUtil.isEmpty(providerId)){
			i_p2 = Integer.parseInt(providerId);
			request.setAttribute("providerId", i_p2);
		}
		if (!CommonUtil.isEmpty(status)){
			condition.setIspay(Integer.parseInt(status));
			request.setAttribute("status", Integer.parseInt(status));
		}
		List <Provider> pList1 = CommonUtil.getProService().getProList(); 
		List <Product> pList2 = CommonUtil.getProductService().getProductList();
		oList = os.getOrderList(pager, condition ,i_p1, i_p2);
		request.setAttribute("oList", oList);
		request.setAttribute("productList", pList2);
		request.setAttribute("providerList", pList1);
		request.setAttribute("pager", pager);
		request.getRequestDispatcher("/jsp/main/mainPart.jsp").forward(request, response);
	}
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
