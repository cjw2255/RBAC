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
import com.woniuxy.pojo.PageBean;
import com.woniuxy.pojo.Permission;
import com.woniuxy.pojo.Role;
import com.woniuxy.service.IPermissionService;
import com.woniuxy.service.IRoleService;

@Controller
@RequestMapping("role")
public class RoleController {
	@Autowired
	IRoleService rs;
	@Autowired
	IPermissionService ps;
	@RequestMapping("show")
	public String index() {
		return "role/show";
	}
	
	
	@RequestMapping("loadData")
	@ResponseBody
	public Object loadData(Integer rid) {
		//ʹ��map���ϴ�����pid��Ϊ����
		Map<Integer,Permission> map = new HashMap<>();
		List<Permission> zTree = new ArrayList<Permission>(); 
		//��ȡ���еĽڵ�
		List<Permission> permissList = ps.findAll();
		//���ݽ�ɫ��ȡ�Ѿ�����Ľڵ�
		List<Integer> assignPermission = ps.assignPermission(rid);
		//�Ƚ����еĽڵ���ӵ�map��
		for (Permission permission : permissList) {
			//�ҵ���ɫ�Ѿ��������ɣ�����������checked����Ϊtrue����ô�Ϳ���Ĭ��ѡ��
			if (assignPermission.contains(permission.getPid())) {
				permission.setChecked(true);
			}else {
				permission.setChecked(false);
			}
			permission.setOpen(true);
			map.put(permission.getPid(), permission);
		}
		//Ȼ����ѭ���������ṹ
		for (Permission permission : permissList) {
			//�����ǰ�ڵ�ĸ��ڵ�Ϊ�գ�����Ϊ���ڵ�
			if (permission.getParentid()==null) { 
				 zTree.add(permission);
			}else {
				//�����map�ҳ���ǰ�ڵ�ĸ��ڵ�p��Ȼ�󽫵�ǰ�Ľڵ���������ڵ�
				Permission p = map.get(permission.getParentid());
				p.getChildren().add(permission);
			}
		}
		return zTree;
	}
	
	@RequestMapping("roleSave")
	public String roleSave() {
		return "role/roleSave";
	}
	
	@RequestMapping("assignPermission")
	public String assignPermission(Integer rid,Model model) {
		model.addAttribute("rid", rid);
		return "role/assignPermission";
	}
	
	@RequestMapping("assignPermissionDo")
	@ResponseBody
	public Object assignPermissionDo(Integer rid,Integer[] pids) {
		Message message = new Message();
		for (Integer pid : pids) {
			System.out.println("RoleController.assignPermissionDo()"+pid);
		}
		Map<String,Object> map = new HashMap<String,Object>();
		try {
			map.put("rid", rid);
			map.put("pids", pids);
			rs.assignPermission(map);
			message.setFlag(true);
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			message.setFlag(false);
		}
		return message;
	}
	@RequestMapping("roleshow")
	@ResponseBody
	public Object roleshow(PageBean<Role> pageBean) {
		Message message = new Message();
		if (pageBean.getCurrentPage()==null) {
			pageBean.setCurrentPage(1);
		}
		pageBean.setPageSize(5);
		int totalCount = rs.countAll(pageBean);
		pageBean.setTotalCount(totalCount);
		int pages = pageBean.getTotalCount()%pageBean.getPageSize()==0?pageBean.getTotalCount()/pageBean.getPageSize():pageBean.getTotalCount()/pageBean.getPageSize()+1;
		pageBean.setPages(pages);
		try {
			List<Role> roleList = rs.findAll(pageBean);
			pageBean.setList(roleList);
			message.setObj(pageBean);
			message.setFlag(true);
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			message.setFlag(false);
		}
		return message;
	}
	@RequestMapping("saveRole")
	@ResponseBody
	public Object saveRole(Role role) {
		Message message = new Message();
		try {
			rs.saveRole(role);
			message.setFlag(true);
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			message.setFlag(false);
		}
		return message;
	}
	@RequestMapping("update")
	public String update(Integer rid,Model model) {
		Message message = new Message();
		try {
			Role role = rs.findOne(rid);
			model.addAttribute("role", role);
			message.setFlag(true);
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			message.setFlag(false);
		}
		return "role/updateRole";
	}
	
	@RequestMapping("updateRole")
	@ResponseBody
	public Object updateRole(Role role) {
		Message message = new Message();
		try {
			rs.updateRole(role);
			message.setFlag(true);
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			message.setFlag(false);
		}
		return message;
	}
	
	@RequestMapping("deleteRole")
	@ResponseBody
	public Object deleteRole(Integer rid,Integer uid) {
		Message message = new Message();
		try {
			rs.deleteRole(rid);
			message.setFlag(true);
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			message.setFlag(false);
		}
		return message;
	}
	
	@RequestMapping("delRoles")
	@ResponseBody
	public Object delRoles(Integer[] rids) {
		Message message = new Message();
		try {
			rs.delRoles(rids);
			message.setFlag(true);
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			message.setFlag(false);
		}
		return message;
	}
}
