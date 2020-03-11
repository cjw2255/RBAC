package com.woniuxy.util;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import com.woniuxy.pojo.Users;

public class LoginInterceptor implements HandlerInterceptor {

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		// TODO Auto-generated method stub
		System.out.println("LoginInterceptor.preHandle()");
		//在这里编写登陆拦截器的功能：如果session有登录用户信息，则允许继续执行，否则跳转到登录界面
		HttpSession session = request.getSession();
		Users users = (Users) session.getAttribute("loginUser");
		if (users!=null) {
			//放行
			return true;
		}else {
			//重定向到登录界面
			response.sendRedirect(request.getServletContext().getContextPath()+"/login");
			//返回false拦截
		}
		return false;
	}

	@Override
	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
			ModelAndView modelAndView) throws Exception {
		// TODO Auto-generated method stub
		System.out.println("LoginInterceptor.postHandle()");
	}

	@Override
	public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex)
			throws Exception {
		// TODO Auto-generated method stub
		System.out.println("LoginInterceptor.afterCompletion()");
	}

}
