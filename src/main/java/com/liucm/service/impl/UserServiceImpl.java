package com.liucm.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import com.liucm.bean.State;
import com.liucm.bean.User;
import com.liucm.bean.UserRole;
import com.liucm.dao.StateMapper;
import com.liucm.dao.UserMapper;
import com.liucm.dao.UserRoleMapper;
import com.liucm.service.MailService;
import com.liucm.service.UserService;

@Service
public class UserServiceImpl implements UserService {

	@Autowired
	private UserMapper userMapper;

	@Autowired
	private UserRoleMapper userRoleMapper;

	@Autowired
	private StateMapper stateMapper;

	@Autowired
	private MailService mailService;
	
	@Cacheable(value="myToken",key="#userName")
	@Override
	public User verifyUser(String userName, String password) {
		if (!userName.equals("") && !password.equals("")) {
			User user = userMapper.selectOneByUserName(userName);
			if (user != null && password.equals(user.getPassword())) {
				return user;
			}
		}
		return null;
	}

	@CacheEvict(value="myToken",allEntries=true)
	@Override
	public boolean addUser(String userName, String password) {
		User user = new User();
		user.setUserName(userName);
		user.setPassword(password);
		user.setIconUrl("\\img\\defaulticon.jpg");
		UserRole userRole = userRoleMapper.selectOneByUserRoleId(2);
		State state = stateMapper.selectStateByStateId(1);
		user.setUserRole(userRole);
		user.setState(state);
		int verify = userMapper.insertUser(user);
		if (verify == 1) {
			return true;
		} else {
			return false;
		}
	}
	
	@Override
	public boolean forget(String userName) {
		User user = null;
		if (!userName.equals("")) {
			 user = userMapper.selectOneByUserName(userName);
		}
		if (user != null) {
			return mailService.sendSimpleMail(user.getUserMail(), "找回密码",
					"尊敬的" + user.getUserName() + "，你好！你的密码为" + user.getPassword() + "，请及时修改你的密码。");
		} else {
			return false;
		}
	}
	
	@Cacheable(value="myToken",key="#userId")
	@Override
	public User getUserByUserId(int userId) {
		if (userId != 0) {
			User user = userMapper.selectOne(userId);
			if(user != null) {
				return user;
			}
		}
		return null;
	}

	@Override
	public boolean updateUser(User user) {
		try {
			userMapper.updateUser(user);
			return true;
		}catch (Exception e) {
			e.printStackTrace();
			return false;
		}	
	}

	
	@CacheEvict(value="myToken",allEntries=true)
	@Override
	public String updatePswd(User user, String oldPswd, String newPswd) {
		
		/**
		 * 输入的旧密码等于该用户的原始密码，旧密码不等于新密码
		 */
		if(user != null && !user.getPassword().equals(oldPswd)) {
			return  "原始密码不正确";
		}
		if(oldPswd.equals(newPswd) ) {
			return "新密码不可已与旧密码相同";
		}
		if (!oldPswd.equals(newPswd) && user != null && user.getPassword().equals(oldPswd)) {
			user.setPassword(newPswd);
			userMapper.updateUser(user);
			return "success";
		} else {
			return "error";
		}
	}

}
