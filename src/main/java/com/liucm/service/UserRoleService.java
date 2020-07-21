package com.liucm.service;

import java.util.List;

import com.liucm.bean.UserRole;

public  interface UserRoleService {
	public List<UserRole> getAllRole();
	public String addRole(String userRoleName);
	public String deleteRoleByRoleId(int roleId);
	public boolean verifyexist(String roleName);
	public UserRole getOneByRoleName(String userRoleName);
	public UserRole getOneByRoleId(int roleId);
}
