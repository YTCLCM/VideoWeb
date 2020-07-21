package com.liucm.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.liucm.bean.UserRole;
import com.liucm.dao.UserRoleMapper;
import com.liucm.service.UserRoleService;
 
@Service
@Transactional
public class UserRoleServiceImpl implements UserRoleService{

	@Autowired
	private UserRoleMapper userRoleMapper;
	
	@Override
	public List<UserRole> getAllRole() {
		return userRoleMapper.selectAll();
	}

	@Override
	public String addRole(String userRoleName) {
		try {
			userRoleMapper.addUserRole(userRoleName);
		} catch (Exception e) {
			e.printStackTrace();
			return "角色添加失敗";
		}
		
		return "角色添加成功";
	}

	@Override
	public String deleteRoleByRoleId(int roleId) {
		try {
			UserRole role = userRoleMapper.selectOneByUserRoleId(roleId);
			if(role != null) {
				userRoleMapper.deleteOne(roleId);
				return "角色刪除成功";
			}else {
				return "该角色已不存在";
			}
			
		} catch (Exception e) {
			return "角色刪除失敗";
		}	
	}

	@Override
	public boolean verifyexist(String roleName) {
		try {
			UserRole role = userRoleMapper.selectOneByRoleName(roleName);
			if(role != null)
				return true;
			else {
				return false;
			}
		} catch (Exception e) {
			return true;
		}
	}

	@Override
	public UserRole getOneByRoleName(String userRoleName) {
		return userRoleMapper.selectOneByRoleName(userRoleName);
	}

	@Override
	public UserRole getOneByRoleId(int roleId) {
		// TODO Auto-generated method stub
		return userRoleMapper.selectOneByUserRoleId(roleId);
	}

}
