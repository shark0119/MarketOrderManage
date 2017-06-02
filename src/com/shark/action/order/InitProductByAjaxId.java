package com.shark.action.order;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.shark.entity.Product;
import com.shark.util.CommonUtil;
import com.alibaba.fastjson.*;


@WebServlet("/order/InitProductByAjaxId")
public class InitProductByAjaxId extends HttpServlet {
	private static final long serialVersionUID = 1L;
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String id = request.getParameter("id");
		System.out.println(id);
		int pid =-1;
		try{
			pid = Integer.parseInt(id);
		}catch (Exception e){
			System.out.println("无效的ID");
			return ;
		}
		List <Product> pList = CommonUtil.getProductService().getProductList(pid);
		String data = JSON.toJSON(pList).toString();
		System.out.println("产品集合为:"+data);
		PrintWriter out= response.getWriter();
		out.write(data);
		out.flush();
		out.close();		
	}
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
