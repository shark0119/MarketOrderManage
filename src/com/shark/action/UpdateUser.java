package com.shark.action;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.shark.entity.Role;
import com.shark.entity.User;
import com.shark.util.CommonUtil;
/**
 * ���ܴ������ط���ת����
 * 1.�û��б�
 * 2.�޸��û��ύ����
 * @author Shark
 */
@WebServlet("/action/UpdateUser")
public class UpdateUser extends HttpServlet {
	private static final long serialVersionUID = 1L;
    public UpdateUser() {
        super();
    }
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String msg = request.getParameter("update");
		if (!CommonUtil.isEmpty(msg) && msg.equals("update")){
			User user = new User();
			try{
				user.setId(Integer.parseInt(request.getParameter("id")));
			}catch(Exception e){
				e.printStackTrace();
			}

			user.setName(request.getParameter("userName"));
			
			user.setSex(request.getParameter("sex"));
			try {
				user.setBirth(new SimpleDateFormat("yyyy-MM-dd").parse(request.getParameter("date")));
			} catch (ParseException e) {
				e.printStackTrace();
				response.getWriter().write("<script>alert('����ʧ��,���ݲ��Ϸ�,���Ժ�����');location.href='/SuperMarket/jsp/main/mainPart.jsp';</script>");
				return;
			}
			user.setMobile(request.getParameter("phone"));
			user.setAddress(request.getParameter("address"));
			user.setRid(Integer.parseInt(request.getParameter("role")));
			System.out.println("update user data:"+user);
			
			//�ж��޸ĵ��Ƿ�Ϊ��ǰ�û�
			int currentId = (int) request.getSession().getAttribute("id");
			if (user.getId() == currentId){
				HttpSession session = request.getSession();
				session.setAttribute("roleid", user.getRid());
				session.setAttribute("id", user.getId());
				session.setAttribute("username", user.getName());
			}
			if (!CommonUtil.getUserService().updateUser(user)){
				response.getWriter().write("<script>alert('����ʧ�ܣ����ݿ�������Ժ�����');location.href='/SuperMarket/jsp/main/mainPart.jsp';</script>");
				return;
			}
			response.sendRedirect("/SuperMarket/action/InitUser");
		}else{
			String uid = request.getParameter("id");
			User user = CommonUtil.getUserService().getUser(Integer.parseInt(uid));
			System.out.println(user);
			request.setAttribute("userDetail", user);
			List<Role> roleList = CommonUtil.getRoleService().getRoleList();
			request.setAttribute("roleList", roleList);
			request.getSession().setAttribute("contentPageName", "updateUser.jsp");
			request.getRequestDispatcher("/jsp/main/mainPart.jsp").forward(request, response);
		}
	}
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
