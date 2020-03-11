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
		//������нڵ�
		
		//ģ�����ṹ
//		List<Permission> permissList = new ArrayList<Permission>();
//		Permission root = new Permission();
//		root.setName("Ȩ�޹���");
//		root.setOpen(true);
//		Permission children1 = new Permission();
//		children1.setName("�û�ά��");
//		Permission children2 = new Permission();
//		children2.setName("��ɫά��");
//		Permission children3 = new Permission();
//		children3.setName("���ά��");
//		root.getChildren().add(children1);
//		root.getChildren().add(children2);
//		root.getChildren().add(children3);
//		permissList.add(root);
		
		
		
		/*
//		 * //����˵�ֻ�����㣬����ʹ�����ַ�ʽ //�ҵ����ڵ� Permission root = ps.findRoot(); //������еĽڵ�
//		 * List<Permission> permissList = ps.findAll(); //ѭ���ж� for (Permission
//		 * permission : permissList) { if (permission.getParentid()==root.getPid()) {
//		 * root.getChildren().add(permission); } } List<Permission> zTree = new
//		 * ArrayList<Permission>(); zTree.add(root); return zTree;
//		 */
		
//		
//		//�ҵ����ڵ� 
//		Permission root = ps.findRoot();
//		//�ݹ鷽ʽ����ӽڵ�
//		queryZTree(root);
//		List<Permission> zTree = new ArrayList<Permission>(); 
//		zTree.add(root); 
//		return zTree;
		
		
		
//		//Ƕ��ѭ���γ����ṹ
//		List<Permission> zTree = new ArrayList<Permission>(); 
//		//��ȡ���еĽڵ�
//		List<Permission> permissList = ps.findAll();
//		for (Permission child : permissList) {
//			//������ڵ�Ϊ�գ�����Ϊ���ڵ�
//			if (child.getParentid()==null) {
//				zTree.add(child);
//			}else {
//				//�������ѭ�����нڵ�����жϣ�Ѱ��child�ĸ��ڵ�������
//				for (Permission p : permissList) {
//					if (child.getParentid()==p.getPid()) {
//						p.getChildren().add(child);
//						break;
//					}
//				}
//			}
//		}
//		return zTree;
		
		
		
		//ʹ��map���ϴ�����pid��Ϊ����
		Map<Integer,Permission> map = new HashMap<>();
		List<Permission> zTree = new ArrayList<Permission>(); 
		//��ȡ���еĽڵ�
		List<Permission> permissList = ps.findAll();
		//�Ƚ����еĽڵ���ӵ�map��
		for (Permission permission : permissList) {
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
	
	//�ݹ�����ӽڵ�
	private void queryZTree(Permission root) {
		// TODO Auto-generated method stub
		//���ݸ��ڵ㣬��Ѱ���ӽڵ�
		List<Permission> children = ps.selectByPid(root.getPid());
		//�ݹ����(����ӽڵ㲻Ϊ�գ���ѭ���ҵ�ÿ���ӽڵ㣬���еݹ����)
		if (children!=null) {
			for (Permission child : children) {
				queryZTree(child);
			}
		}
		//���ӽڵ���ӵ����ڵ���
		root.setChildren(children);
	}
}
