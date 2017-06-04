package com.shark.action.provider;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.shark.entity.Pager;
import com.shark.entity.Provider;
import com.shark.service.ProService;
import com.shark.util.CommonUtil;

@WebServlet("/pro/InitPro")
public class InitPro extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private Pager pager = null;
	private Provider condition;
	private ProService ps;

	public InitPro() {
		ps = CommonUtil.getProService();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		condition = new Provider();
		request.getSession().setAttribute("contentPageName", "./provider/proList.jsp");
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
		String pname = request.getParameter("s_proName");
		List<Provider> proList;
		if (!CommonUtil.isEmpty(pname)) {// 有条件时分页查询
			pname = new String(pname.getBytes("ISO-8859-1"), "UTF-8");
			condition.setName(pname);
			proList = ps.getProList(pager, condition);
		} else {// 无条件时分页查询
			proList = ps.getProList(pager);
		}
		request.setAttribute("pList", proList);
		request.setAttribute("pager", pager);
		request.setAttribute("condition", condition);
		request.getRequestDispatcher("/jsp/main/mainPart.jsp").forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}
}
