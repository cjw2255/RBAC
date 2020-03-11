package com.woniuxy.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.woniuxy.dao.PermissionMapper;
import com.woniuxy.pojo.Permission;
import com.woniuxy.service.IPermissionService;
@Service
@Transactional
public class PermissionServiceImpl implements IPermissionService {
	@Autowired
	PermissionMapper pm;

	@Override
	public List<Permission> findAll() {
		// TODO Auto-generated method stub
		List<Permission> permissList = pm.findAll();
		return permissList;
	}

	@Override
	public Permission findRoot() {
		// TODO Auto-generated method stub
		Permission root = pm.findRoot();
		return root;
	}

	@Override
	public List<Permission> selectByPid(Integer pid) {
		// TODO Auto-generated method stub
		List<Permission> children = pm.selectByPid(pid);
		return children;
	}

	@Override
	public void permissionAdd(Permission permissioin) {
		// TODO Auto-generated method stub
		pm.permissionAdd(permissioin);
	}

	@Override
	public Permission findOne(Integer pid) {
		// TODO Auto-generated method stub
		Permission permission = pm.findOne(pid);
		return permission;
	}

	@Override
	public void permissionUpdate(Permission permissioin) {
		// TODO Auto-generated method stub
		pm.permissionUpdate(permissioin);
	}

	@Override
	public void permissionDel(Integer pid) {
		// TODO Auto-generated method stub
		pm.permissionDel(pid);
	}

	@Override
	public List<Integer> assignPermission(Integer rid) {
		// TODO Auto-generated method stub
		List<Integer> pids = pm.assignPermission(rid);
		return pids;
	}
}
