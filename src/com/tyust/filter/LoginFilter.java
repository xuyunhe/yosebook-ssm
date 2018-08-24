package com.tyust.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;

public class LoginFilter implements Filter {

	public void destroy() {
		
	}

	public void doFilter(ServletRequest req,
			ServletResponse res,
			FilterChain chain)
					throws IOException, ServletException {
		HttpServletRequest request = (HttpServletRequest) req;
		Object user = request.getSession().getAttribute("sessionUser");
		if(user==null){
			request.setAttribute("code", "error");
			request.setAttribute("msg", "您还未登录！");
			request.getRequestDispatcher("/jsps/msg.jsp").forward(request, res);
		}else{
			chain.doFilter(request, res);
		}
	}
	
	public void init(FilterConfig config) throws ServletException {
		
	}

}
