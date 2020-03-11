package com.woniuxy.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.woniuxy.pojo.Message;
import com.woniuxy.pojo.Permission;
import com.woniuxy.service.IPermissionService;

@Controller
@RequestMapping("permission")
public class PermissionController {
	@Autowired
	IPermissionService ps;
	@RequestMapping("permission")
	public String permission() {
		return "permission/permission";
	}
	
	@RequestMapping("permissionSave")
	public String permissionSave(Integer pid,Model model) {
		model.addAttribute("pid", pid);
		return "permission/permissionSave";
	}
	
	@RequestMapping("permissionFindOne")
	public String permissionFindOne(Integer pid,Model model) {
		Permission permission = ps.findOne(pid);
		model.addAttribute("permission", permission);
		return "permission/permissionUpdate";
	}
	
	@RequestMapping("permissionUpdate")
	@ResponseBody
	public Object permissionUpdate(Permission permissioin) {
		Message message = new Message();
		try {
			ps.permissionUpdate(permissioin);
			message.setFlag(true);
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			message.setFlag(false);
		}
		return message;
	}
	
	@RequestMapping("permissionDel")
	@ResponseBody
	public Object permissionDel(Integer pid) {
		Message message = new Message();
		try {
			ps.permissionDel(pid);
			message.setFlag(true);
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			message.setFlag(false);
		}
		return message;
	}
	@RequestMapping("permissionAdd")
	@ResponseBody
	public Object permissionAdd(Permission permissioin) {
		Message message = new Message();
		try {
			ps.permissionAdd(permissioin);
			message.setFlag(true);
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			message.setFlag(false);
		}
		return message;
	}
	
	@RequestMapping("loadData")
	@ResponseBody
	public Object loadData() {
		//获得所有节点
		
		//模拟树结构
//		List<Permission> permissList = new ArrayList<Permission>();
//		Permission root = new Permission();
//		root.setName("权限管理");
//		root.setOpen(true);
//		Permission children1 = new Permission();
//		children1.setName("用户维护");
//		Permission children2 = new Permission();
//		children2.setName("角色维护");
//		Permission children3 = new Permission();
//		children3.setName("许可维护");
//		root.getChildren().add(children1);
//		root.getChildren().add(children2);
//		root.getChildren().add(children3);
//		permissList.add(root);
		
		
		
		/*
//		 * //如果菜单只有两层，可以使用这种方式 //找到跟节点 Permission root = ps.findRoot(); //获得所有的节点
//		 * List<Permission> permissList = ps.findAll(); //循环判断 for (Permission
//		 * permission : permissList) { if (permission.getParentid()==root.getPid()) {
//		 * root.getChildren().add(permission); } } List<Permission> zTree = new
//		 * ArrayList<Permission>(); zTree.add(root); return zTree;
//		 */
		
//		
//		//找到跟节点 
//		Permission root = ps.findRoot();
//		//递归方式添加子节点
//		queryZTree(root);
//		List<Permission> zTree = new ArrayList<Permission>(); 
//		zTree.add(root); 
//		return zTree;
		
		
		
//		//嵌套循环形成数结构
//		List<Permission> zTree = new ArrayList<Permission>(); 
//		//获取所有的节点
//		List<Permission> permissList = ps.findAll();
//		for (Permission child : permissList) {
//			//如果父节点为空，则作为根节点
//			if (child.getParentid()==null) {
//				zTree.add(child);
//			}else {
//				//否则继续循环所有节点进行判断，寻找child的父节点进行添加
//				for (Permission p : permissList) {
//					if (child.getParentid()==p.getPid()) {
//						p.getChildren().add(child);
//						break;
//					}
//				}
//			}
//		}
//		return zTree;
		
		
		
		//使用map集合处理，将pid作为索引
		Map<Integer,Permission> map = new HashMap<>();
		List<Permission> zTree = new ArrayList<Permission>(); 
		//获取所有的节点
		List<Permission> permissList = ps.findAll();
		//先将所有的节点添加到map中
		for (Permission permission : permissList) {
			permission.setOpen(true);
			map.put(permission.getPid(), permission);
		}
		//然后再循环生成树结构
		for (Permission permission : permissList) {
			//如果当前节点的父节点为空，则作为根节点
			if (permission.getParentid()==null) { 
				 zTree.add(permission);
			}else {
				//否则从map找出当前节点的父节点p，然后将当前的节点添加至父节点
				Permission p = map.get(permission.getParentid());
				p.getChildren().add(permission);
			}
		}
		return zTree;
	}
	
	//递归添加子节点
	private void queryZTree(Permission root) {
		// TODO Auto-generated method stub
		//根据根节点，找寻其子节点
		List<Permission> children = ps.selectByPid(root.getPid());
		//递归遍历(如果子节点不为空，则循环找到每个子节点，进行递归操作)
		if (children!=null) {
			for (Permission child : children) {
				queryZTree(child);
			}
		}
		//将子节点添加到根节点中
		root.setChildren(children);
	}
}
