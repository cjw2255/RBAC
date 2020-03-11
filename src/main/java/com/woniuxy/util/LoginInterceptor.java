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
		//�������д��½�������Ĺ��ܣ����session�е�¼�û���Ϣ�����������ִ�У�������ת����¼����
		HttpSession session = request.getSession();
		Users users = (Users) session.getAttribute("loginUser");
		if (users!=null) {
			//����
			return true;
		}else {
			//�ض��򵽵�¼����
			response.sendRedirect(request.getServletContext().getContextPath()+"/login");
			//����false����
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
