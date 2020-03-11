package com.woniuxy.dao;

import java.util.List;
import java.util.Map;

import com.woniuxy.pojo.PageBean;
import com.woniuxy.pojo.Role;

public interface RoleMapper {

	List<Role> findAll(PageBean<Role> pageBean);

	int countAll(PageBean<Role> pageBean);

	void saveRole(Role role);

	Role findOne(Integer rid);

	void updateRole(Role role);

	void deleteRole(Integer rid);

	void delRoles(Integer[] rids);

	List<Role> selectAll(Integer uid);

	List<Role> findAllocation(Integer uid);

	void assignPermission(Map<String, Object> map);

	void assignPermissionDel(Object object);


}
