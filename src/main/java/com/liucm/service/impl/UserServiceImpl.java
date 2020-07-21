package com.liucm.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.stereotype.Service;

import com.liucm.bean.State;
import com.liucm.bean.User;
import com.liucm.bean.UserRole;
import com.liucm.controller.UserController;
import com.liucm.dao.StateMapper;
import com.liucm.dao.UserMapper;
import com.liucm.dao.UserRoleMapper;
import com.liucm.service.MailService;
import com.liucm.service.UserService;
import com.liucm.util.Page;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
@CacheConfig
public class UserServiceImpl implements UserService {

	@Autowired
	private UserMapper userMapper;

	@Autowired
	private UserRoleMapper userRoleMapper;

	@Autowired
	private StateMapper stateMapper;

	@Autowired
	private MailService mailService;

	// @Cacheable(value="myToken",key="#userName")
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
	
	@CachePut(value = "myToken", key = "#userName")
	@Override
	public boolean addUser(String userName, String password) {
		
		if (userMapper.selectOneByUserName(userName) == null) {
			log.info("用户注册：userName:password == "+userName+":"+password);
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
		return false;
	}

	@Override
	public boolean addUser(User user) {
		try {
			int verify = userMapper.insertUser(user);
			if (verify == 1) {
				return true;
			} else {
				return false;
			}
		} catch (Exception e) {
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

	@Override
	public User getUserByUserId(int userId) {
		if (userId != 0) {
			User user = userMapper.selectOne(userId);
			if (user != null) {
				return user;
			}
		}
		return null;
	}

	@CacheEvict(value = "myToken", allEntries = true)
	@Override
	public boolean updateUser(User user) {
		try {
			userMapper.updateUser(user);
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}

	@CacheEvict(value = "myToken", allEntries = true)
	@Override
	public String updatePswd(User user, String oldPswd, String newPswd) {

		/**
		 * 输入的旧密码等于该用户的原始密码，旧密码不等于新密码
		 */
		if (user == null || oldPswd.equals("") || newPswd.equals("")) {
			return "用户不存在，或输入数据不完整";
		} else if (!user.getPassword().equals(oldPswd)) {
			return "原始密码不正确";
		} else if (oldPswd.equals(newPswd)) {
			return "新密码与旧密码相同，更换新密码！";
		} else {
			user.setPassword(newPswd);
			userMapper.updateUser(user);
			return "密码修改成功";
		}
	}

	@Override
	public String delUser(int userId) {
		User user = userMapper.selectOne(userId);
		if (user != null) {
			userMapper.deleteUserById(userId);
			return "用户删除成功";
		} else {
			return "该用户不存在";
		}
	}

	/**
	 * 分页查询用户
	 */
	@Override
	public String getUserByPage(Page<User> page) {
		page.setTotalSize(userMapper.selectUserSum());
		page.setData(userMapper.selectUserByPage(page));
		return "查询成功";
	}

}
