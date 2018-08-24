package com.tyust.admin.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;

import com.tyust.admin.entity.Admin;

public class AdminLoginFilter implements Filter {
	// 1自定义拦截器，就是普通的类集成了filter

	public void destroy() {

	}

	public void doFilter(ServletRequest req, ServletResponse res, FilterChain chain)
			throws IOException, ServletException {
		HttpServletRequest request = (HttpServletRequest) req;
		// 2进行强制转换为httpservletRequest;?????---不转换可以吗？？.....但验证。。。。。。。。。。
		StringBuffer url = request.getRequestURL();
		// 3得到请求的URL，为什么是stringbuffer接受呢............................
		String path = url.toString();
		if (path.endsWith("login.do")) {
			chain.doFilter(request, res);
		} else {
			Admin admin = (Admin) request.getSession().getAttribute("sessionAdmin");
			// 4得到session中的value,是后台管理员的信息；
			if (admin == null) {
				request.setAttribute("msg", "您还未登录！");
				request.getRequestDispatcher("/adminjsps/login.jsp").forward(request, res);
				;
				// 5如果sesion中的value为null顺明后台管理员还没哟哟登录，重新转发到登录页面；
			} else {
				chain.doFilter(request, res);
				// 6否则放行，不进行拦截；
			}
		}
	}

	public void init(FilterConfig fConfig) throws ServletException {

	}

}
