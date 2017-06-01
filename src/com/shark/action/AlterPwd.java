package com.shark.action;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.shark.entity.User;
import com.shark.util.CommonUtil;
/**
 * �������ط���ת����
 * 1.mainPart.jsp
 * 2.alterPwd.jsp
 * @author Shark
 *
 */
@WebServlet("/action/AlterPwd")
public class AlterPwd extends HttpServlet {
	private static final long serialVersionUID = 1L;
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String str = request.getParameter("alterPwd");
		if (CommonUtil.isEmpty(str)){//from mainPart.jsp
			request.getSession().setAttribute("contentPageName", "alterPwd.jsp");
			request.getRequestDispatcher("/jsp/main/mainPart.jsp").forward(request, response);
		}else{//from alterPwd.jsp
			String newPwd = request.getParameter("newPassword");
			Integer id = (Integer) request.getSession().getAttribute("id");
			User user = CommonUtil.getUserService().getUser(id);
			user.setPwd(newPwd);
			PrintWriter out = response.getWriter();
			if (!CommonUtil.getUserService().updateUser(user)){
				//����ʧ��
				out.write("{\"success\": false,\"msg\": \"�޸�ʧ�ܣ����Ժ�����\"}");
			}else{
				out.write("{\"success\": true,\"msg\": \"�޸ĳɹ�\"}");				
			}	
			out.flush();
			out.close();
		}
	}
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
