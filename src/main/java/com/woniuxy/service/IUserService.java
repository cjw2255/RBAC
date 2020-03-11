package com.woniuxy.service;

import java.util.List;
import java.util.Map;

import com.woniuxy.pojo.PageBean;
import com.woniuxy.pojo.Permission;
import com.woniuxy.pojo.Users;

public interface IUserService {

	Users findUser(Users users);

	List<Users> findAll();

	List<Users> findByPage(PageBean<Users> pageBean);

	int countAll(PageBean<Users> pageBean);

	void saveUser(Users users);

	void update(Integer uid);

	Users findOne(Integer uid);

	void updateUser(Users users);

	void deleteUser(Integer uid);

	void delUsers(Integer[] uids);

	void assignRoles(Map<String, Object> map);

	void assignRoleDel(Map<String, Object> map);

	List<Permission> selectPermissionByUser(Users loginUser);

}
