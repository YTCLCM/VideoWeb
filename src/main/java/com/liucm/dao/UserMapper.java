package com.liucm.dao;

import java.util.List;

import com.liucm.bean.User;

public interface UserMapper {
	public List<User> selectAll(); 
	
	public User selectOne(int userId);
	
	public User selectOneByUserName(String userName);
	
	public int insertUser(User user);
	
	public int updateUser(User user);
	
	public int deleteUserById(int userId);
}
