package com.liucm.daotest;

import java.util.List;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.liucm.ApplicationTest;
import com.liucm.bean.UserRole;
import com.liucm.dao.UserRoleMapper;

public class UserRoleTest extends ApplicationTest {

	@Autowired
	private UserRoleMapper userRoleMapper;
	
	@Test
	public void TestSelectAll() {
		List<UserRole> userRoles = userRoleMapper.selectAll();
		for(UserRole userRole:userRoles) {
			System.out.println(userRole);
		}
	}
	
	@Test
	public void TestSelectOneByUserRoleId() {
		UserRole userRole = userRoleMapper.selectOneByUserRoleId(1);
		System.out.println(userRole);
	}

}
