package com.liucm.dao;

import java.util.List;

import com.liucm.bean.Focus;
import com.liucm.bean.User;

public interface FocusMapper {
	
	/**
	 * 该用户关注的人
	 * @param userId
	 * @return
	 */
	public List<User> selectFocuseds(int userId);
	
	public List<Integer> selectFocusedsId(int userId);
	
	/**
	 * 关注该用户的人
	 * @param userId
	 * @return
	 */
	public List<User> selectFocuses(int userId);
	
	public int selectOneVerify(Focus focus);
	
	public int insertOne(Focus focus);
	
	public int deleteOne(Focus focus);
}
