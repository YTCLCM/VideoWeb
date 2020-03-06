package com.liucm.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.liucm.bean.UserRole;
import com.liucm.dao.UserRoleMapper;
import com.liucm.service.UserRoleService;
 
@Service
public class UserRoleServiceImpl implements UserRoleService{

	@Autowired
	private UserRoleMapper userRoleMapper;
	
	@Override
	public List<UserRole> getAllRole() {
		return userRoleMapper.selectAll();
	}

}
