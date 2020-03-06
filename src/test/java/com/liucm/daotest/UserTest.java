package com.liucm.daotest;

import java.util.Date;
import java.util.List;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.liucm.ApplicationTest;
import com.liucm.bean.State;
import com.liucm.bean.User;
import com.liucm.bean.UserRole;
import com.liucm.dao.StateMapper;
import com.liucm.dao.UserMapper;
import com.liucm.dao.UserRoleMapper;
import com.liucm.util.DateUtil;

public class UserTest extends ApplicationTest {

	@Autowired
	private UserMapper userMapper;
	@Autowired
	private UserRoleMapper userRoleMapper;
	@Autowired
	private StateMapper stateMapper;

	@Test
	public void TestSelectAll() {
		List<User> users = userMapper.selectAll();
		for (User user : users) {
			System.out.println(user.toString());
			Date date = user.getRegisterDate();
			System.out.println(new DateUtil().getFormatDate(null, date));
		}
	}

	@Test
	public void TestInsertUser() {
		User user = new User();
		UserRole userRole = userRoleMapper.selectOneByUserRoleId(1);
		State state = stateMapper.selectStateByStateId(1);
		user.setUserName("liucm");
		user.setUserAge(24);
		user.setUserSex("男");
		user.setUserMail("13576921363@qq.com");
		user.setUserPhone("13576921363");
		user.setUserAddress("鹰潭市");
		user.setPassword("123456");
		user.setRegisterDate(null);
		user.setFanNum(0);
		user.setIconUrl("liucm.jpg");
		user.setState(state);
		user.setUserRole(userRole);
		userMapper.insertUser(user);// 永远返回的是1
		System.out.println(user.getUserId());// 会放入user对象中
	}

	@Test
	public void TestDeleteUserById() {
		userMapper.deleteUserById(7);
	}

	@Test
	public void TestSelectOneByUserName() {
		User user = userMapper.selectOneByUserName("LCM");
		System.out.println(user);
	}
}
