package com.liucm.dao;

import java.util.List;

import com.liucm.bean.UserRole;

public interface UserRoleMapper {
	public List<UserRole> selectAll();
	
	public UserRole selectOneByUserRoleId(Integer roleId);
}
