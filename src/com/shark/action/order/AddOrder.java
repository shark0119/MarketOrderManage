package com.shark.action.order;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.shark.entity.Order;
import com.shark.entity.Provider;
import com.shark.util.CommonUtil;
import com.shark.util.OtherServiceUtil;
/**
 * ��Դ 1.�����б������ 2.���ҳ�����ύ
 * 
 * @author Shark
 *
 */
@WebServlet("/order/AddOrder")
public class AddOrder extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		if (!CommonUtil.isEmpty(request.getParameter("operation")) && request.getParameter("operation").equals("add")){
			//��Դ2 2.���ҳ�����ύ
			Order order =new Order();
			String productId = request.getParameter("productId");
			String providerId = request.getParameter("supplier");
			int cp_id =-1;
			if (!CommonUtil.isEmpty(providerId) && !CommonUtil.isEmpty(productId))
				cp_id = OtherServiceUtil.getCP_id(
					Integer.parseInt(providerId), Integer.parseInt(productId));
			String count = request.getParameter("billNum");
			String desc = request.getParameter("desc");
			if (CommonUtil.isEmpty(desc))
				desc = "������";
			String ispay = request.getParameter("status");
			String money = request.getParameter("totalPrice");
			if (cp_id < 0){
				return;
			}
			order.setC_pid(cp_id);
			order.setCount(Integer.parseInt(count));
			order.setIspay(Integer.parseInt(ispay));
			order.setMoney(Integer.parseInt(money));
			order.setDesc(desc);
			System.out.println("������ļ�¼:"+order);
			if (!CommonUtil.getOrderService().addOrder(order)){
				response.getWriter().write("<script>alert('���ʧ�ܣ����ݿ�������Ժ�����');location.href='/SuperMarket/jsp/main/mainPart.jsp';</script>");
				return;
			}
			response.sendRedirect("/SuperMarket/order/InitOrder");
		}else {
			//��Դ1.�����б������
			request.getSession().setAttribute("contentPageName", "./order/OrderDetail.jsp");
			List <Provider> pList = CommonUtil.getProService().getProList();
			request.setAttribute("providerList", pList);
			request.setAttribute("operation", "add");
			request.setAttribute("jumpPage", "AddOrder");
			request.getRequestDispatcher("/jsp/main/mainPart.jsp").forward(request, response);
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}
}
