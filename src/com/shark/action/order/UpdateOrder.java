package com.shark.action.order;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.shark.entity.Order;
import com.shark.entity.Product;
import com.shark.entity.Provider;
import com.shark.util.CommonUtil;
import com.shark.util.OtherServiceUtil;

/**
 * 来源
 * 1.订单列表点击修改
 * 2.修改页面点击提交
 * @author Shark
 *
 */
@WebServlet("/order/UpdateOrder")
public class UpdateOrder extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		if (!CommonUtil.isEmpty(request.getParameter("operation")) && request.getParameter("operation").equals("update")){
			//来源2 2.添加页面点击提交
			Order order =new Order();
			String productId = request.getParameter("productId");
			String providerId = request.getParameter("supplier");
			int cp_id = OtherServiceUtil.getCP_id(
					Integer.parseInt(providerId), Integer.parseInt(productId));
			String count = request.getParameter("billNum");
			String desc = request.getParameter("desc");
			if (CommonUtil.isEmpty(desc))
				desc = "无描述";
			String ispay = request.getParameter("status");
			String money = request.getParameter("totalPrice");
			String id = request.getParameter("id");
			if (cp_id < 0){
				return;
			}
			
			order.setId(Integer.parseInt(id));
			order.setC_pid(cp_id);
			order.setCount(Integer.parseInt(count));
			order.setIspay(Integer.parseInt(ispay));
			order.setMoney(Integer.parseInt(money));
			order.setDesc(desc);
			System.out.println("待插入的记录:"+order);
			if (!CommonUtil.getOrderService().addOrder(order)){
				response.getWriter().write("<script>alert('添加失败，数据库错误，请稍后再试');location.href='/SuperMarket/jsp/main/mainPart.jsp';</script>");
				return;
			}
			response.sendRedirect("/SuperMarket/order/InitOrder");
		}else {
			//来源1.订单列表点击添加
			request.getSession().setAttribute("contentPageName", "./order/OrderDetail.jsp");
			List <Provider> pList = CommonUtil.getProService().getProList();
			String id = request.getParameter("id");
			int providerId = OtherServiceUtil.getProviderId(Integer.parseInt(id));
			int productId = OtherServiceUtil.getProductId(Integer.parseInt(id));
			int c_pid = OtherServiceUtil.getCP_id(providerId, productId);
			Product p = CommonUtil.getProductService().getProductByC_Pid(c_pid);
			List <Product> pList2 = CommonUtil.getProductService().getProductList(providerId);
			Order order = CommonUtil.getOrderService().getOrder(Integer.parseInt(id));
			p.setPrice(order.getMoney()/order.getCount());
			
			request.setAttribute("u_providerId", providerId);
			request.setAttribute("u_productId", productId);
			request.setAttribute("u_product", p);
		
			request.setAttribute("u_order", order);
			request.setAttribute("providerList", pList);
			request.setAttribute("u_productList", pList2);
			request.setAttribute("operation", "update");
			request.setAttribute("jumpPage", "UpdateOrder");
			request.getRequestDispatcher("/jsp/main/mainPart.jsp").forward(request, response);
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
