package com.woniuxy.util;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;
/**
 * 许可拦截器
 * @author Bruse
 *
 */

import com.woniuxy.pojo.Permission;
import com.woniuxy.pojo.Users;
import com.woniuxy.service.impl.PermissionServiceImpl;
import com.woniuxy.service.impl.UserServiceImpl;
public class PermissionInterceptor implements HandlerInterceptor {
	@Autowired
	PermissionServiceImpl ps;
	@Autowired
	UserServiceImpl us;
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		// TODO Auto-generated method stub
		//获得请求信息的路径
		String requestUrI = request.getRequestURI();
		//获得所有的许可
		List<Permission> permissionList = ps.findAll();
		String contextPath = request.getServletContext().getContextPath();
		boolean flag = false;
		//判断请求信息是否正常（当前的请求信息是否在所有的许可中，不在则请求信息错误）
		for (Permission permission : permissionList) {
			if ((contextPath+permission.getUrl()).equals(requestUrI)) {
				//说明当前请求信息正常
				flag = true;
				break;
			}
		}
		if (flag) {
			//获得当前登录用户的信息
			HttpSession session = request.getSession();
			Users loginUser = (Users) session.getAttribute("loginUser");
			//获取当前登录用户的许可信息
			List<Permission> userPermission = us.selectPermissionByUser(loginUser);
			//判断请求信息是否是当前登录用户所拥有的许可
			for (Permission permission : userPermission) {
				if ((contextPath+permission.getUrl()).equals(requestUrI)) {
					//成立则放行
					return true;
				}
			}
			String message = "1";
			response.sendRedirect(contextPath+"/error?message="+message);
		}else {
			//用户请求的信息是错误的，不存在该许可
			String message = "2";
			response.sendRedirect(contextPath+"/error?message="+message);
		}
		return false;
	}

	@Override
	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
			ModelAndView modelAndView) throws Exception {
		// TODO Auto-generated method stub

	}

	@Override
	public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex)
			throws Exception {
		// TODO Auto-generated method stub

	}

}
