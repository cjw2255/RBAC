package com.woniuxy.util;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;
/**
 * ���������
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
		//���������Ϣ��·��
		String requestUrI = request.getRequestURI();
		//������е����
		List<Permission> permissionList = ps.findAll();
		String contextPath = request.getServletContext().getContextPath();
		boolean flag = false;
		//�ж�������Ϣ�Ƿ���������ǰ��������Ϣ�Ƿ������е�����У�������������Ϣ����
		for (Permission permission : permissionList) {
			if ((contextPath+permission.getUrl()).equals(requestUrI)) {
				//˵����ǰ������Ϣ����
				flag = true;
				break;
			}
		}
		if (flag) {
			//��õ�ǰ��¼�û�����Ϣ
			HttpSession session = request.getSession();
			Users loginUser = (Users) session.getAttribute("loginUser");
			//��ȡ��ǰ��¼�û��������Ϣ
			List<Permission> userPermission = us.selectPermissionByUser(loginUser);
			//�ж�������Ϣ�Ƿ��ǵ�ǰ��¼�û���ӵ�е����
			for (Permission permission : userPermission) {
				if ((contextPath+permission.getUrl()).equals(requestUrI)) {
					//���������
					return true;
				}
			}
			String message = "1";
			response.sendRedirect(contextPath+"/error?message="+message);
		}else {
			//�û��������Ϣ�Ǵ���ģ������ڸ����
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
