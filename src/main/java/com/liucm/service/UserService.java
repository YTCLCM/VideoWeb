package com.liucm.service;

import com.liucm.bean.User;

public interface UserService {
	public User verifyUser(String userName,String password);
	public boolean addUser(String userName,String password);
	public boolean updateUser(User user);
	public String updatePswd(User user,String oldPswd,String newPswd);
	public boolean forget(String userName);
	public User getUserByUserId(int userId);
}
