package com.shark.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
/**
 * 确认用户无法在未登录的情况下访问指定资源
 * @author Shark
 *
 */
public class CommonLogin implements Filter{

	@Override
	public void init(FilterConfig filterConfig) throws ServletException {
	}

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		//System.out.println("login verify filter");
		HttpServletResponse hsr = (HttpServletResponse) response;
		Object flag = ((HttpServletRequest)request).getSession().getAttribute("login");
		if (flag != null && flag.equals("true")){
			chain.doFilter(request, response);
		}else{
			hsr.sendRedirect("/SuperMarket/jsp/login.jsp");
		}
	}

	@Override
	public void destroy() {
	}

}
