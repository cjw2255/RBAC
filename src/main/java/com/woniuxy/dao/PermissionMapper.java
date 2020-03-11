package com.woniuxy.dao;

import java.util.List;

import com.woniuxy.pojo.Permission;

public interface PermissionMapper {

	List<Permission> findAll();

	Permission findRoot();

	List<Permission> selectByPid(Integer pid);

	void permissionAdd(Permission permissioin);

	Permission findOne(Integer pid);

	void permissionUpdate(Permission permissioin);

	void permissionDel(Integer pid);

	List<Integer> assignPermission(Integer rid);

}
