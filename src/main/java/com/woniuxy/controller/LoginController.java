package com.woniuxy.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.woniuxy.pojo.Message;
import com.woniuxy.pojo.Permission;
import com.woniuxy.pojo.Users;
import com.woniuxy.service.IUserService;

@Controller
public class LoginController {
	@Autowired
	IUserService us;
	@RequestMapping("loginjsp")
	public String loginjsp(Users users,HttpSession session,Model model) {
		Users loginUser = us.findUser(users);
		//�����¼�û���Ϊ�գ����¼�ɹ�
		if (loginUser!=null) {
			//����¼�û���Ϣ����session
			session.setAttribute("loginUser", loginUser);
			return "menu";
		}
		model.addAttribute("message", "�û����������");
		//����û�Ϊ�գ����¼ʧ��
		return "redirect:login";
	}
	
	
	
	//ajax����
	@RequestMapping("login")
	public String login() {
		return "login";
	}
	@RequestMapping("menu")
	public String menu() {
		return "menu";
	}
	@RequestMapping("error")
	public String error() {
		return "error";
	}
	@RequestMapping("loginout")
	public String loginout(HttpSession session) {
		//��¼�û��˳���¼  session.removeAttribute("loginUser");�Ƴ�session�е�ĳ������
		//session.removeAttribute("loginUser");
		//���session�е���������(������¼�˵���session)
		session.invalidate();
		return "redirect:login";
	}
	
	@RequestMapping("loginajax")
	@ResponseBody
	public Object loginajax(Users users,HttpSession session) {
		//����Service�еĵ�¼���������ص�¼�û��Ķ���
		Users loginUser = us.findUser(users);
		Message message = new Message();
		if (loginUser!=null) {
			session.setAttribute("loginUser", loginUser);
			//���ݵ�¼�û������ӵ�е����
			List<Permission> permissList = us.selectPermissionByUser(loginUser);
			Permission root=null;
			Map<Integer,Permission> map = new HashMap<Integer,Permission>();
			//����ѭ�������еĽڵ������map������
			for (Permission permission : permissList) {	
				permission.setOpen(true);
				map.put(permission.getPid(), permission);
			}
			for (Permission permission : permissList) {
				if (permission.getParentid()==null) {
					root = permission;
				}else {
					Permission parent = map.get(permission.getParentid());
					parent.getChildren().add(permission);
					
				}
			}
			//����¼�û��Ĳ˵�����session
			session.setAttribute("root", root);
		
			message.setFlag(true);
		}else {
			message.setFlag(false);
		}
		return message;
	}
}
