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
		//如果登录用户不为空，则登录成功
		if (loginUser!=null) {
			//将登录用户信息存入session
			session.setAttribute("loginUser", loginUser);
			return "menu";
		}
		model.addAttribute("message", "用户名密码错误！");
		//如果用户为空，则登录失败
		return "redirect:login";
	}
	
	
	
	//ajax操作
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
		//登录用户退出登录  session.removeAttribute("loginUser");移除session中的某个数据
		//session.removeAttribute("loginUser");
		//清除session中的所有数据(包括登录菜单的session)
		session.invalidate();
		return "redirect:login";
	}
	
	@RequestMapping("loginajax")
	@ResponseBody
	public Object loginajax(Users users,HttpSession session) {
		//调用Service中的登录方法，返回登录用户的对象
		Users loginUser = us.findUser(users);
		Message message = new Message();
		if (loginUser!=null) {
			session.setAttribute("loginUser", loginUser);
			//根据登录用户获得其拥有的许可
			List<Permission> permissList = us.selectPermissionByUser(loginUser);
			Permission root=null;
			Map<Integer,Permission> map = new HashMap<Integer,Permission>();
			//利用循环将所有的节点添加至map集合中
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
			//将登录用户的菜单存入session
			session.setAttribute("root", root);
		
			message.setFlag(true);
		}else {
			message.setFlag(false);
		}
		return message;
	}
}
