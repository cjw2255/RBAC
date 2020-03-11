package com.woniuxy.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.woniuxy.dao.RoleMapper;
import com.woniuxy.pojo.PageBean;
import com.woniuxy.pojo.Role;
import com.woniuxy.service.IRoleService;
@Service
@Transactional
public class RoleServiceImpl implements IRoleService {
	@Autowired
	RoleMapper roleMapper;

	@Override
	public List<Role> findAll(PageBean<Role> pageBean) {
		// TODO Auto-generated method stub
		List<Role> roleList = roleMapper.findAll(pageBean);
		return roleList;
	}

	@Override
	public int countAll(PageBean<Role> pageBean) {
		// TODO Auto-generated method stub
		int totalCount = roleMapper.countAll(pageBean);
		return totalCount;
	}

	@Override
	public void saveRole(Role role) {
		// TODO Auto-generated method stub
		roleMapper.saveRole(role);
	}

	@Override
	public Role findOne(Integer rid) {
		// TODO Auto-generated method stub
		Role role = roleMapper.findOne(rid);
		return role;
	}

	@Override
	public void updateRole(Role role) {
		// TODO Auto-generated method stub
		roleMapper.updateRole(role);
	}

	@Override
	public void deleteRole(Integer rid) {
		// TODO Auto-generated method stub
		roleMapper.deleteRole(rid);
	}

	@Override
	public void delRoles(Integer[] rids) {
		// TODO Auto-generated method stub
		roleMapper.delRoles(rids);
	}

	@Override
	public List<Role> selectAll(Integer uid) {
		// TODO Auto-generated method stub
		List<Role> roleList = roleMapper.selectAll(uid);
		return roleList;
	}

	@Override
	public List<Role> findAllocation(Integer uid) {
		// TODO Auto-generated method stub
		List<Role> roleList = roleMapper.findAllocation(uid);
		return roleList;
	}

	@Override
	public void assignPermission(Map<String, Object> map) {
		// TODO Auto-generated method stub
		roleMapper.assignPermissionDel(map.get("rid"));
		roleMapper.assignPermission(map);
	}
}
