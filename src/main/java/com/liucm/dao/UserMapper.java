package com.liucm.dao;

import java.util.List;

import org.apache.ibatis.annotations.Select;

import com.liucm.bean.User;
import com.liucm.util.Page;

public interface UserMapper {
	public List<User> selectAll(); 
	
	public List<Integer> selectAllUserId(); 
	
	public User selectOne(int userId);
	
	public User selectOneByUserName(String userName);
	
	public int insertUser(User user);
	
	public int updateUser(User user);
	
	public int deleteUserById(int userId);
	
	@Select("select count(*) from tb_user")
	public int selectUserSum();
	
	public List<User> selectUserByPage(Page<User> page);
}
