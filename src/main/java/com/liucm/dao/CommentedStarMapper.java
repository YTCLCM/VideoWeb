package com.liucm.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.liucm.bean.CommentedStar;

public interface CommentedStarMapper {
	public int insertOne(@Param(value="userId")int userId,@Param(value="videoId")int videoId,@Param(value="starNum")int starNum);
	public List<CommentedStar> selectAll();
	public CommentedStar getCommentedStar(int userId,int videoId);
}
